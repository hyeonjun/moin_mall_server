package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductItemVO {

  private Long productId;
  private List<ItemVO> items;

  public static ProductItemVO valueOf(Product product, List<ItemVO> items) {
    ProductItemVO vo = new ProductItemVO();

    vo.setProductId(product.getId());
    vo.setItems(items);

    return vo;
  }
}
