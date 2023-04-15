package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Option;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OptionVO {

  private Long id;
  private String name;
  private String value;
  private Integer orderBy;

  @QueryProjection
  public OptionVO(Long id, String name, String value, Integer orderBy) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.orderBy = orderBy;
  }

  public static OptionVO valueOf(Option option) {
    OptionVO vo = new OptionVO();

    vo.setId(option.getId());
    vo.setName(option.getName());
    vo.setValue(option.getValue());
    vo.setOrderBy(option.getOrderBy());

    return vo;
  }
}
