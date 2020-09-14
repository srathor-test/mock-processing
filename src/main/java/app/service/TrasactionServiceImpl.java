package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.dao.Items;
import app.dao.ItemsRepository;
import app.dao.TransactionRecord;
import app.dao.TransactionsRepository;
import app.exception.BusinessException;
import io.swagger.model.Item;
import io.swagger.model.ModelApiResponse;
import io.swagger.model.Transaction;

@Component
public class TrasactionServiceImpl implements TrasactionService {

	private static final Logger log = LoggerFactory.getLogger(TrasactionServiceImpl.class);

	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Autowired
	private ItemsRepository itemsRepository;

	@Override
	public ResponseEntity<List<Transaction>> retrieveTransaction(String transactionId
			, String merchantId) {
		log.info("retrieveTransaction invoked");
		List<TransactionRecord> resultset = null;
		if(transactionId == null && merchantId == null) {
			resultset = transactionsRepository.findAll();
		} else if(transactionId != null && merchantId != null) {
			resultset = transactionsRepository.findByMerchantIdAndTransactionId(merchantId, transactionId);
		} else if(transactionId == null && merchantId != null) {
			resultset = transactionsRepository.findByMerchantId(merchantId);
		} else if(transactionId != null && merchantId == null) {
			resultset = transactionsRepository.findByTransactionId(transactionId);
		}
		
		Map<String, List<TransactionRecord>> groupList = resultset.stream().collect(Collectors.groupingBy(TransactionRecord::getTransactionId));
		Set<String> transactionIds = groupList.keySet();
		
		List<Transaction> result = mapTransactionsResult(groupList, transactionIds);
		return ResponseEntity.ok(result);
	}

	private List<Transaction> mapTransactionsResult(Map<String, List<TransactionRecord>> groupList,
			Set<String> transactionIds) {
		List<Transaction> result = new ArrayList<>();
		transactionIds.forEach( tid -> {
			Transaction t = new Transaction();
			result.add(t);
			List<TransactionRecord> tgroup = groupList.get(tid);
			TransactionRecord r = tgroup.get(0);
			t.setMerchantId(r .getMerchantId());
			t.setTransactionId(r.getTransactionId());
			t.setTransactionAmount(r.getTransactionAmount());
			t.setTransactionCurrency(r.getTransactionCurrency());
			t.setCardNumber(r.getCardNumber());
			t.setCardExpirydate(r.getCardExpirydate());
			t.setCardCsv(r.getCardCsv());
			List<Item> items = new ArrayList<>();
			t.setItems(items);
			tgroup.forEach( tg -> {
				Item item = new Item();
				items.add(item);
				item.setItemId(tg.getItemId().toString());
				item.setItemCount(tg.getItemCount().toString());
			});
		});
		return result;
	}
	
	@Override
	@Transactional
	public ResponseEntity<ModelApiResponse> receiveTransaction(Transaction transaction) {
		log.info("receiveTransaction invoked");
		List<Items> lockedItems = new ArrayList<>();
		List<Item> lockedLowStockItems = new ArrayList<>();
		List<Items> lockedLowStockItemsAvail = new ArrayList<>();
		List<String> missingItems = new ArrayList<>();
		List<Item> items = transaction.getItems();
		items.forEach(i -> { 
			try {
				Items item = itemsRepository.findOneByMerchantIdAndItemId(transaction.getMerchantId(), i.getItemId());
				if(item != null) {
					lockedItems.add(item);
					if(Integer.parseInt(i.getItemCount()) > item.getItemCount()) {
						lockedLowStockItems.add(i);
						lockedLowStockItemsAvail.add(item);
					}
				}
			} catch(Exception e) {
				log.warn("item not found exception", e);
				missingItems.add(i.getItemId());
			}
		});
		
		if(lockedItems.isEmpty()) {
			throw new BusinessException("Cannot complete order, none of the item is present with the merchant");
		}
		
		if(lockedLowStockItems.size() > 0) {
			StringBuilder message = new StringBuilder();
			message.append("Cannot complete order, low on stock for items( ");
			for(int i = 0; i < lockedLowStockItems.size(); i++) {
				message.append("itemId: " + lockedLowStockItems.get(i).getItemId() 
						+ ", Requested itemCount: " + lockedLowStockItems.get(i).getItemCount() 
						+ ", Available itemCount: " + lockedLowStockItemsAvail.get(i).getItemCount() + "; ");
			}
			message.append(")");
			throw new BusinessException(message.toString());
		}
		
		for(int i = 0; i < lockedItems.size(); i++) {
			lockedItems.get(i).setItemCount(lockedItems.get(i).getItemCount() - Integer.parseInt(items.get(i).getItemCount()));
			itemsRepository.saveAndFlush(lockedItems.get(i));
		}
		
		for(int i = 0; i < items.size(); i++) {
			TransactionRecord entity = populateTransactionRecordEntity(transaction, items, i);
			transactionsRepository.saveAndFlush(entity);
		}
		
		ModelApiResponse response = new ModelApiResponse();
		response.setCode(200);
		response.setMessage("SUCCESS");
		response.setType("SUCCESS");
		return  ResponseEntity.ok(response);
	}

	private TransactionRecord populateTransactionRecordEntity(Transaction transaction, List<Item> items, int i) {
		TransactionRecord entity = new TransactionRecord();
		entity.setMerchantId(transaction.getMerchantId());
		entity.setTransactionId(transaction.getTransactionId());
		entity.setTransactionAmount(transaction.getTransactionAmount());
		entity.setTransactionCurrency(transaction.getTransactionCurrency());
		entity.setCardNumber(transaction.getCardNumber());
		entity.setCardExpirydate(transaction.getCardExpirydate());
		entity.setCardCsv(transaction.getCardCsv());
		entity.setItemId(Integer.parseInt(items.get(i).getItemId()));
		entity.setItemCount(Integer.parseInt(items.get(i).getItemCount()));
		return entity;
	}

}
