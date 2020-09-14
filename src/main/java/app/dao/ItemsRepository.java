package app.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface ItemsRepository extends JpaRepository<Items, Long> {

	
	List<Items> findByMerchantId(String merchantId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Items findOneByMerchantIdAndItemId(String merchantId, String itemId);
	
}

