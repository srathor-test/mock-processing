package app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import io.swagger.model.ModelApiResponse;
import io.swagger.model.Transaction;

public interface TrasactionService {
	
	ResponseEntity<List<Transaction>> retrieveTransaction(String transactionId, String merchantId);

	ResponseEntity<ModelApiResponse> receiveTransaction(Transaction transaction);


}
