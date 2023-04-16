package com.buying.back.application.product.repository;

import static com.buying.back.application.product.domain.QOption.option;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.service.vo.OptionVO;
import com.buying.back.application.product.service.vo.QOptionVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import java.util.List;
import java.util.Set;

public class OptionRepositoryImpl extends CustomQuerydslRepositorySupport implements
  OptionRepositoryCustom {

  public OptionRepositoryImpl() {
    super(Option.class);
  }

  @Override
  public List<OptionVO> findAllOptionIdIn(Set<Long> optionsIds) {
    return select(getOptionVO())
      .from(option)
      .where(option.id.in(optionsIds))
      .fetch();
  }

  private QOptionVO getOptionVO() {
    return new QOptionVO(
      option.id,
      option.name,
      option.value,
      option.orderBy
    );
  }


}
