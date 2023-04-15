package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailVO extends ProductDefaultVO {
  private List<ItemVO> items;

  private ProductDetailVO(Product product) {
    super(product);
  }

  public static ProductDetailVO valueOf(Product product, List<ItemVO> items) {
    ProductDetailVO vo = new ProductDetailVO(product);
    vo.setItems(items);
    return vo;
  }
}
