package com.buying.back.application.account.repository;

import static com.buying.back.application.account.domain.QBrand.brand;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.service.vo.BrandEnterpriseListVO;
import com.buying.back.application.account.service.vo.QBrandEnterpriseListVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BrandRepositoryImpl extends CustomQuerydslRepositorySupport
  implements BrandRepositoryCustom {


  public BrandRepositoryImpl() {
    super(Brand.class);
  }

  @Override
  public Page<BrandEnterpriseListVO> findAllEnterprise(Pageable pageable,
    SearchBrandEnterpriseManagementDTO dto) {
    BooleanBuilder whereCondition = getBrandEnterpriseWhereCondition(dto);

    JPAQuery<BrandEnterpriseListVO> query = select(getBrandEnterpriseVO())
      .from(brand)
      .where(whereCondition)
      .orderBy(brand.id.asc());

    JPAQuery<Long> countQuery = select(brand.count())
      .from(brand)
      .where(whereCondition);

    return applyPagination(pageable, query, countQuery);
  }

  private QBrandEnterpriseListVO getBrandEnterpriseVO() {
    return new QBrandEnterpriseListVO(
      brand.id,
      brand.brandName,
      brand.url,
      brand.representativeName,
      brand.representativeEmail,
      brand.activated
    );
  }

  private BooleanBuilder getBrandEnterpriseWhereCondition(SearchBrandEnterpriseManagementDTO dto) {
    BooleanBuilder whereCondition = new BooleanBuilder();

    if (Objects.nonNull(dto.getActivated())) {
      whereCondition.and(brand.activated.eq(dto.getActivated()));
    }

    if (Objects.nonNull(dto.getBrandName())) {
      whereCondition.and(brand.brandName.contains(dto.getBrandName()));
    }

    if (Objects.nonNull(dto.getRepresentativeName())) {
      whereCondition.and(brand.representativeName.contains(dto.getRepresentativeName()));
    }

    if (Objects.nonNull(dto.getUrl())) {
      whereCondition.and(brand.url.contains(dto.getUrl()));
    }


    return whereCondition;
  }
}
