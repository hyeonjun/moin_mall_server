package com.buying.back.application.product.repository;

import com.buying.back.application.product.repository.param.SearchProductListParam;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
  Page<ProductDefaultVO> findAll(Pageable pageable, SearchProductListParam param);
}
