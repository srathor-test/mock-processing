package app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionRecord, Long> {
	
	List<TransactionRecord> findByMerchantId(String merchantId);
	List<TransactionRecord> findByTransactionId(String transactionId);
	List<TransactionRecord> findByMerchantIdAndTransactionId(String merchantId, String transactionId);

}
