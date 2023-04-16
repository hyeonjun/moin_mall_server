package com.buying.back.application.product.repository;

import com.buying.back.application.product.repository.param.SearchProductListParam;
import com.buying.back.application.product.service.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
  Page<ProductVO> findAllByBrand(Pageable pageable, SearchProductListParam param);
}
