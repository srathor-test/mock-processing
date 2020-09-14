package app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import app.dao.Items;
import app.dao.ItemsRepository;
import app.dao.Merchant;
import app.dao.MerchantRepository;
import app.exception.BusinessException;
import io.swagger.model.Item;
import io.swagger.model.MerchantInventory;
import io.swagger.model.MerchantInventoryItem;


@Component
public class StoreServiceImpl implements StoreService {
	private static final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ResponseEntity<MerchantInventory> getInventory(String merchantId) {
		log.info("getInventory invoked");
		List<Items> items = null;
		if(merchantId == null) {
			items = itemsRepository.findAll();
		} else {
			items = itemsRepository.findByMerchantId(merchantId);
		}
		
		MerchantInventory merchantInventory = new MerchantInventory();
		items.forEach(item -> {
			MerchantInventoryItem merchantItem = new MerchantInventoryItem();
			merchantInventory.add(merchantItem);
			merchantItem.setMerchantId(Long.parseLong(item.getMerchantId()));
			merchantItem.setItemId(new Long(item.getItemId()));
			merchantItem.setItemCount(new Long(item.getItemCount()));
		});		
		return ResponseEntity.ok(merchantInventory);
	}

	@Override
	@Transactional
	public ResponseEntity<Item> addItemInventory(String merchantId, Item item) {
		log.info("addItemInventory invoked");
		List<Merchant> merchant = merchantRepository.findByMerchantId(merchantId);
		if(merchant.isEmpty()) {
			throw new BusinessException("merchantId not found");
		}
		
		//Items result = entityManager.find(Items.class, item.getItemId(), LockModeType.PESSIMISTIC_WRITE);
		
		Items result = itemsRepository.findOneByMerchantIdAndItemId(merchantId, item.getItemId());
		if(result == null) {
			Items newObj = new Items();			
			newObj.setMerchantId(merchantId);
			newObj.setItemId(item.getItemId());
			Integer itemCount = Integer.parseInt(item.getItemCount());
			newObj.setItemCount(itemCount);
			newObj.setId(Integer.parseInt(item.getItemId()));
			itemsRepository.saveAndFlush(newObj);
		} else {
			Integer itemCount = Integer.parseInt(item.getItemCount());
			Integer newItemCount = result.getItemCount() + itemCount;
			item.setItemCount(newItemCount.toString());
			result.setItemCount(newItemCount);
			itemsRepository.saveAndFlush(result);
		}
		return ResponseEntity.ok(item);
	}



}
