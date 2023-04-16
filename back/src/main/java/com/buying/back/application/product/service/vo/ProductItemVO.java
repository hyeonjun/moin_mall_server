package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductItemVO extends ProductVO {
  
  private List<ItemVO> items;

  private ProductItemVO(Product product) {
    super(product);
  }

  public static ProductItemVO valueOf(Product product, List<ItemVO> items) {
    ProductItemVO vo = new ProductItemVO(product);

    vo.setItems(items);

    return vo;
  }
}
