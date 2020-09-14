package app.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionRecord {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
	private String merchantId;	
	private String  transactionId;
	private String  transactionAmount; 
	private String  transactionCurrency;
	private String  cardNumber;
	private String  cardExpirydate;
	private String  cardCsv;	
	private Integer itemId;	
	private Integer itemCount;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionCurrency() {
		return transactionCurrency;
	}
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpirydate() {
		return cardExpirydate;
	}
	public void setCardExpirydate(String cardExpirydate) {
		this.cardExpirydate = cardExpirydate;
	}
	public String getCardCsv() {
		return cardCsv;
	}
	public void setCardCsv(String cardCsv) {
		this.cardCsv = cardCsv;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

}
