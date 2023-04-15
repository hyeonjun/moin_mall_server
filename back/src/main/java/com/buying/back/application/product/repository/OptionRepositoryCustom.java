package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import com.buying.back.application.product.service.vo.OptionDefaultVO;

import java.util.List;
import java.util.Set;

public interface OptionRepositoryCustom {
    List<OptionDefaultVO> findDistinctNameByProduct(Product product);

    List<ItemOptionVO> findOptionsByIdIn(Set<Long> ids);
}
