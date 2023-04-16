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
  private String itemName;
  private Integer itemPrice;
  private Integer itemQuantity;
  private Integer itemDiscountPrice;
  private Integer itemDiscountRate;
  private List<OptionVO> options;

  public static ItemVO valueOf(Item item, List<OptionVO> options) {
    ItemVO vo = new ItemVO();

    vo.setItemId(item.getId());
    vo.setItemName(item.getName());
    vo.setItemPrice(item.getPrice());
    vo.setItemQuantity(item.getQuantity());
    vo.setItemDiscountPrice(item.getDiscountPrice());
    vo.setItemDiscountRate(item.getDiscountRate());

    vo.setOptions(options);

    return vo;
  }
}
