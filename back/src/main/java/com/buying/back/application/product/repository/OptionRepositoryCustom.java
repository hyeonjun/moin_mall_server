package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.OptionVO;

import java.util.List;
import java.util.Set;

public interface OptionRepositoryCustom {
    List<OptionVO> findDistinctNameByProduct(Product product);

    List<ItemVO> findOptionsByIdIn(Set<Long> ids);
}
