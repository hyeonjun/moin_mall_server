package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.param.SearchProductListParam;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import com.buying.back.application.product.service.vo.QProductDefaultVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.Objects;

import static com.buying.back.application.product.domain.QProduct.product;

public class ProductRepositoryImpl extends CustomQuerydslRepositorySupport implements ProductRepositoryCustom {
  public ProductRepositoryImpl() {
    super(Product.class);
  }

  @Override
  public Page<ProductDefaultVO> findAll(Pageable pageable, SearchProductListParam param) {
    JPAQuery<ProductDefaultVO> productDefaultVOJPAQuery = select(productDefaultVO())
      .from(product)
      .where(getProductWhereCondition(param))
      .orderBy(product.id.desc());

    JPAQuery<Long> countQuery = select(product.count())
      .from(product)
      .where(getProductWhereCondition(param));

    return applyPagination(pageable, productDefaultVOJPAQuery, countQuery);
  }

  private BooleanBuilder getProductWhereCondition(SearchProductListParam param) {
    BooleanBuilder whereCondition = new BooleanBuilder();
    if (StringUtils.hasText(param.getProductName())) {
      whereCondition.and(product.name.contains(param.getProductName()));
    }

    if (StringUtils.hasText(param.getBrandName())) {
      // TODO: 2023-03-12 Brand 추가 하고 추가
    }
    if (Objects.nonNull(param.getCategoryId())) {
      // TODO: 2023-03-12 Category 추가 하고 추가
    }

    if (Objects.nonNull(param.getCreatedDateFrom())) {
      whereCondition.and(product.createdDateTime.goe(
        param.getCreatedDateFrom().atTime(LocalTime.MIN)
      ));
    }

    if (Objects.nonNull(param.getCreatedDateFrom())) {
      whereCondition.and(product.createdDateTime.lt(
        param.getCreatedDateTo().plusDays(1).atTime(LocalTime.MIN)
      ));
    }

    if (Objects.nonNull(param.getPriceFrom())) {
      whereCondition.and(product.price.goe(
        param.getPriceFrom()
      ));
    }

    if (Objects.nonNull(param.getPriceTo())) {
      whereCondition.and(product.price.lt(
        param.getPriceTo() + 1
      ));
    }

    if (Objects.nonNull(param.getDeleted())) {
      whereCondition.and(product.deleted.eq(param.getDeleted()));
    }

    return whereCondition;
  }

  private QProductDefaultVO productDefaultVO() {
    return new QProductDefaultVO(
      product.id,
      product.name,
      product.price
    );
  }
}
