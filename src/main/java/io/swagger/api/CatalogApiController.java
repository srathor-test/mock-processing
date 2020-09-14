package io.swagger.api;

import io.swagger.model.Item;
import io.swagger.model.MerchantInventory;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.service.StoreService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T06:37:24.371Z")

@Controller
@Validated
public class CatalogApiController implements CatalogApi {

    private static final Logger log = LoggerFactory.getLogger(CatalogApiController.class);
    
    @Autowired
	private StoreService storeService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CatalogApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<MerchantInventory> catalogGet(
    		@ApiParam(value = "merchant id") @Valid 
    		@Pattern(regexp = "^[0-9]{1,9}$", message = "{query.merchantId.pattern}") 
    		@RequestParam(value = "merchantId", required = false) String merchantId) {
    	return storeService.getInventory(merchantId);
    }

    public ResponseEntity<Item> catalogPost(
    		@ApiParam(value = "merchant id",required=true) 
    		@Pattern(regexp = "^[0-9]{1,9}$", message = "{path.merchantId.pattern}") 
    		@PathVariable("merchantId") String merchantId,
    		@ApiParam(value = "order placed for purchasing the pet" ,required=true ) 
    		@Valid @RequestBody Item item) {
        return storeService.addItemInventory(merchantId, item);
    }

}
