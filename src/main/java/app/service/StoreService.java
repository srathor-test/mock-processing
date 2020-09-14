package app.service;

import org.springframework.http.ResponseEntity;

import io.swagger.model.Item;
import io.swagger.model.MerchantInventory;

public interface StoreService {

	ResponseEntity<MerchantInventory> getInventory(String merchantId);

	ResponseEntity<Item> addItemInventory(String merchantId, Item item);

}
