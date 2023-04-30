package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Item;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ItemVO {

  private Long itemId;
  private String itemOptions;
  private Integer itemAdditionalPrice;
  private Integer itemStockQuantity;
  private Integer itemDiscountPrice;
  private Double itemDiscountRate;

  public static ItemVO valueOf(Item item) {
    ItemVO vo = new ItemVO();

    vo.setItemId(item.getId());
    vo.setItemOptions(item.getOptions());
    vo.setItemAdditionalPrice(item.getAdditionalPrice());
    vo.setItemStockQuantity(item.getStockQuantity());
    vo.setItemDiscountPrice(item.getDiscountPrice());
    vo.setItemDiscountRate(item.getDiscountRate());
    return vo;
  }
}
