package io.swagger.api;

import io.swagger.model.ModelApiResponse;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.dao.DedupTx;
import app.dao.DedupTxRepository;
import app.dao.Merchant;
import app.dao.MerchantRepository;
import app.exception.BusinessException;
import app.service.TrasactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T06:37:24.371Z")

@Controller
@Validated
public class TransactionApiController implements TransactionApi {

	private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

	@Autowired
	private TrasactionService trasactionService;

	@Autowired
	private DedupTxRepository dedupTxRepository;

	@Autowired
	private MerchantRepository merchantRepository;
	
	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<List<Transaction>> transactionsGet(String correlationId,
			@Pattern(regexp = "^[0-9]{1,20}$", message = "{query.transactionId.pattern}") String transactionId,
			@Pattern(regexp = "^[0-9]{1,9}$", message = "{query.merchantId.pattern}") String merchantId) {
		return trasactionService.retrieveTransaction(transactionId, merchantId);
	}
	
	@Override
	public ResponseEntity<ModelApiResponse> transactions(String correlationId,
			@Valid Transaction transaction) {
		checkDuplicateTransaction(correlationId, transaction);
		checkIfMerchantExists(transaction.getMerchantId());
		checkValidCardExpiry(transaction.getCardExpirydate());
		return trasactionService.receiveTransaction(transaction);
	}

	private void checkValidCardExpiry(@NotNull @Valid String cardExpirydate) {
		String[] result = cardExpirydate.split("/");
		Integer monthCard = Integer.parseInt(result[0]);
		Integer yearCard = Integer.parseInt(result[1]);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) % 100;
		int month = calendar.get(Calendar.MONTH) + 1;
		if(yearCard < year) {
			throw new BusinessException("card expird(check cardExpirydate)");
		} else if(yearCard == year && monthCard < month) {
			throw new BusinessException("card expird(check cardExpirydate)");
		}
	}

	private void checkIfMerchantExists(String merchantId) {
		Merchant merchant = merchantRepository.findOneByMerchantId(merchantId);
		if(merchant == null) {
			throw new BusinessException("merchantId Not Found");
		}		
	}

	private void checkDuplicateTransaction(String correlationId, Transaction transaction) {
		DedupTx duplicate = dedupTxRepository.findOneByCorrelationIdAndTransactionId(correlationId, transaction.getTransactionId());
		if(duplicate != null) {
			throw new BusinessException("Duplicate Transaction(check correlation id and transaction id)");
		} else {
			DedupTx entity = new DedupTx();
			entity.setCorrelationId(correlationId);
			entity.setTransactionId(transaction.getTransactionId());
			dedupTxRepository.save(entity);
		}
	}

}
