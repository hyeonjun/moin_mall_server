package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Item;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ItemVO {

  private Long id;
  private String name;
  private Integer price;
  private Integer quantity;
  private Integer discountPrice;
  private Integer discountRate;
  private List<OptionVO> options;

  public static ItemVO valueOf(Item item, List<OptionVO> options) {
    ItemVO vo = new ItemVO();

    vo.setId(item.getId());
    vo.setName(item.getName());
    vo.setPrice(item.getPrice());
    vo.setQuantity(item.getQuantity());
    vo.setDiscountPrice(item.getDiscountPrice());
    vo.setDiscountRate(item.getDiscountRate());
    vo.setOptions(options);

    return vo;
  }
}
