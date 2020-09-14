package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DedupTxRepository extends JpaRepository<DedupTx, Long> {
	
	DedupTx findOneByCorrelationIdAndTransactionId(String correlationId, String transactionId);

}
