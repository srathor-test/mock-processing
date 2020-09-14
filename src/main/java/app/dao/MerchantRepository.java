package app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository  extends JpaRepository<Merchant, Long> {

	List<Merchant> findByMerchantId(String merchantId);
	Merchant findOneByMerchantId(String merchantId);
}
