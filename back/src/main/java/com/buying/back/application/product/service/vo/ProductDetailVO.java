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
  private List<ItemDetailVO> items;
  private List<OptionDefaultVO> options;

  public ProductDetailVO(Product product, List<ItemDetailVO> items, List<OptionDefaultVO> options) {
    super(product);
    this.items = items;
    this.options = options;
  }

  public void add(ItemDetailVO itemDefaultVO) {
    items.add(itemDefaultVO);
  }
}
