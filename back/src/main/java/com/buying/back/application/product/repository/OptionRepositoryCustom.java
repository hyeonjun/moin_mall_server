package com.buying.back.application.product.repository;

import com.buying.back.application.product.service.vo.OptionVO;
import java.util.List;
import java.util.Set;

public interface OptionRepositoryCustom {

  List<OptionVO> findAllOptionIdIn(Set<Long> optionIds);
}
