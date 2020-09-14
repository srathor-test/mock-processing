package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * MerchantInventoryItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T06:37:24.371Z")

public class MerchantInventoryItem   {
  @JsonProperty("merchantId")
  private Long merchantId = null;

  @JsonProperty("itemId")
  private Long itemId = null;

  @JsonProperty("itemCount")
  private Long itemCount = null;

  public MerchantInventoryItem merchantId(Long merchantId) {
    this.merchantId = merchantId;
    return this;
  }

  /**
   * Get merchantId
   * @return merchantId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Long merchantId) {
    this.merchantId = merchantId;
  }

  public MerchantInventoryItem itemId(Long itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Get itemId
   * @return itemId
  **/
  @ApiModelProperty(value = "")


  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public MerchantInventoryItem itemCount(Long itemCount) {
    this.itemCount = itemCount;
    return this;
  }

  /**
   * Get itemCount
   * @return itemCount
  **/
  @ApiModelProperty(value = "")


  public Long getItemCount() {
    return itemCount;
  }

  public void setItemCount(Long itemCount) {
    this.itemCount = itemCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchantInventoryItem merchantInventoryItem = (MerchantInventoryItem) o;
    return Objects.equals(this.merchantId, merchantInventoryItem.merchantId) &&
        Objects.equals(this.itemId, merchantInventoryItem.itemId) &&
        Objects.equals(this.itemCount, merchantInventoryItem.itemCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantId, itemId, itemCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantInventoryItem {\n");
    
    sb.append("    merchantId: ").append(toIndentedString(merchantId)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    itemCount: ").append(toIndentedString(itemCount)).append("\n");
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

