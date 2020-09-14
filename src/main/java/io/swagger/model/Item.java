package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T06:37:24.371Z")


public class Item   {
  @JsonProperty("itemId")
  @Pattern(regexp = "^[0-9]{1,9}$", message = "{item.itemId.pattern}")
  @NotNull(message = "{item.itemId.notNull}")
  private String itemId = null;

  @JsonProperty("itemCount")
  @Pattern(regexp = "^[0-9]{1,9}$", message = "{item.itemCount.pattern}")
  @NotNull(message = "{item.itemCount.notNull}")
  private String itemCount = null;

  public Item itemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Get itemId
   * @return itemId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Item itemCount(String itemCount) {
    this.itemCount = itemCount;
    return this;
  }

  /**
   * Get itemCount
   * @return itemCount
  **/
  @ApiModelProperty(value = "")


  public String getItemCount() {
    return itemCount;
  }

  public void setItemCount(String itemCount) {
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
    Item item = (Item) o;
    return Objects.equals(this.itemId, item.itemId) &&
        Objects.equals(this.itemCount, item.itemCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, itemCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
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

