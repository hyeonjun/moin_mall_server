package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemDetailVO extends ItemDefaultVO {
  private List<ItemOptionVO> options;

  public ItemDetailVO(Item item, List<ItemOptionVO> options) {
    super(item);
    this.options = options;
  }

  public ItemDetailVO(List<ItemOptionVO> options) {
    this.options = options;
  }
}
