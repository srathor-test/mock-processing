package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T06:37:24.371Z")


public class Transaction   {

	@JsonProperty("merchantId")
	@NotNull(message = "{transaction.merchantId.notNull}")
	@Pattern(regexp = "^[0-9]{1,9}$", message = "{transaction.merchantId.pattern}")
	private String merchantId = null;

	@JsonProperty("transactionId")
	@NotNull(message = "{transaction.transactionId.notNull}")
	@Pattern(regexp = "^[0-9]{1,20}$", message = "{transaction.transactionId.pattern}")
	private String transactionId = null;

	@JsonProperty("transactionAmount")
	@NotNull(message = "{transaction.transactionAmount.notNull}")
	@Pattern(regexp = "[0-9]{1,15}(\\.[0-9][0-9]?)?", message = "{transaction.transactionAmount.pattern}")
	private String transactionAmount = null;

	@JsonProperty("transactionCurrency")
	@NotNull(message = "{transaction.transactionCurrency.notNull}")
	@Pattern(regexp="[Aa][Uu][Dd]", message = "{transaction.transactionCurrency.pattern}") 
	private String transactionCurrency = null;

	@JsonProperty("cardNumber")
	@NotNull(message = "{transaction.cardNumber.notNull}")
	@Pattern(regexp = "^[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}$", message = "{transaction.cardNumber.pattern}")
	private String cardNumber = null;

	@JsonProperty("cardExpirydate")
	@NotNull(message = "{transaction.cardExpirydate.notNull}")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/[0-9]{2}$", message = "{transaction.cardExpirydate.pattern}")
	private String cardExpirydate = null;

	@JsonProperty("cardCsv")
	@NotNull(message = "{transaction.cardCsv.notNull}")
	@Pattern(regexp="^[0-9][0-9][0-9]$", message = "{transaction.cardExpirydate.pattern}") 
	private String cardCsv = null;

	@JsonProperty("items")
	@Valid
	@NotNull(message = "{transaction.items.notNull}")
	private List<Item> items = new ArrayList<Item>();

	public Transaction merchantId(String merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	/**
	 * Get merchantId
	 * @return merchantId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Transaction transactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	/**
	 * Get transactionId
	 * @return transactionId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Transaction transactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
		return this;
	}

	/**
	 * Get transactionAmount
	 * @return transactionAmount
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull


	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Transaction transactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
		return this;
	}

	/**
	 * Get transactionCurrency
	 * @return transactionCurrency
	 **/
	@ApiModelProperty(example = "AUD", required = true, value = "")
	@NotNull


	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public Transaction cardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		return this;
	}

	/**
	 * Get cardNumber
	 * @return cardNumber
	 **/
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Transaction cardExpirydate(String cardExpirydate) {
		this.cardExpirydate = cardExpirydate;
		return this;
	}

	/**
	 * Get cardExpirydate
	 * @return cardExpirydate
	 **/
	@ApiModelProperty(example = "01/27", required = true, value = "")
	@NotNull
	@Valid
	public String getCardExpirydate() {
		return cardExpirydate;
	}

	public void setCardExpirydate(String cardExpirydate) {
		this.cardExpirydate = cardExpirydate;
	}

	public Transaction cardCsv(String cardCsv) {
		this.cardCsv = cardCsv;
		return this;
	}

	/**
	 * Get cardCsv
	 * @return cardCsv
	 **/
	@ApiModelProperty(example = "123", required = true, value = "")
	public String getCardCsv() {
		return cardCsv;
	}

	public void setCardCsv(String cardCsv) {
		this.cardCsv = cardCsv;
	}

	public Transaction items(List<Item> items) {
		this.items = items;
		return this;
	}

	public Transaction addItemsItem(Item itemsItem) {
		this.items.add(itemsItem);
		return this;
	}

	/**
	 * Get items
	 * @return items
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Transaction transaction = (Transaction) o;
		return Objects.equals(this.merchantId, transaction.merchantId) &&
				Objects.equals(this.transactionId, transaction.transactionId) &&
				Objects.equals(this.transactionAmount, transaction.transactionAmount) &&
				Objects.equals(this.transactionCurrency, transaction.transactionCurrency) &&
				Objects.equals(this.cardNumber, transaction.cardNumber) &&
				Objects.equals(this.cardExpirydate, transaction.cardExpirydate) &&
				Objects.equals(this.cardCsv, transaction.cardCsv) &&
				Objects.equals(this.items, transaction.items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(merchantId, transactionId, transactionAmount, transactionCurrency, cardNumber, cardExpirydate, cardCsv, items);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Transaction {\n");

		sb.append("    merchantId: ").append(toIndentedString(merchantId)).append("\n");
		sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
		sb.append("    transactionAmount: ").append(toIndentedString(transactionAmount)).append("\n");
		sb.append("    transactionCurrency: ").append(toIndentedString(transactionCurrency)).append("\n");
		sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
		sb.append("    cardExpirydate: ").append(toIndentedString(cardExpirydate)).append("\n");
		sb.append("    cardCsv: ").append(toIndentedString(cardCsv)).append("\n");
		sb.append("    items: ").append(toIndentedString(items)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

