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

  private Long optionId;
  private String optionName;
  private String optionValue;
  private Integer optionOrderBy;

  @QueryProjection
  public OptionVO(Long id, String name, String value, Integer orderBy) {
    this.optionId = id;
    this.optionName = name;
    this.optionValue = value;
    this.optionOrderBy = orderBy;
  }

  public static OptionVO valueOf(Option option) {
    OptionVO vo = new OptionVO();

    vo.setOptionId(option.getId());
    vo.setOptionName(option.getName());
    vo.setOptionValue(option.getValue());
    vo.setOptionOrderBy(option.getOrderBy());

    return vo;
  }
}
