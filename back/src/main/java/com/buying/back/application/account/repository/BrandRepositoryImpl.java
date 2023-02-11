package com.buying.back.application.account.repository;

import static com.buying.back.application.account.domain.QAccount.account;
import static com.buying.back.application.account.domain.QBrand.brand;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.service.vo.BrandAccountManagementVO;
import com.buying.back.application.account.service.vo.BrandEnterpriseManagementVO;
import com.buying.back.application.account.service.vo.QBrandAccountManagementVO;
import com.buying.back.application.account.service.vo.QBrandEnterpriseManagementVO;
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
  public Page<BrandEnterpriseManagementVO> findAllEnterprise(Pageable pageable,
    SearchBrandEnterpriseManagementDTO dto) {
    BooleanBuilder whereCondition = getBrandEnterpriseWhereCondition(dto);

    JPAQuery<BrandEnterpriseManagementVO> query = select(getBrandEnterpriseVO())
      .from(brand)
      .where(whereCondition)
      .orderBy(brand.id.asc());

    JPAQuery<Long> countQuery = select(brand.count())
      .from(brand)
      .where(whereCondition);

    return applyPagination(pageable, query, countQuery);
  }

  @Override
  public Page<BrandAccountManagementVO> findAllAccountByBrandId(Pageable pageable, Long brandId) {
    JPAQuery<BrandAccountManagementVO> query = select(getBrandAccountVO())
      .from(brand)
      .leftJoin(account).on(account.brand.id.eq(brand.id))
      .where(brand.id.eq(brandId))
      .orderBy(account.id.asc());

    JPAQuery<Long> countQuery = select(account.count())
      .from(brand)
      .leftJoin(account).on(account.brand.id.eq(brand.id))
      .where(brand.id.eq(brandId));

    return applyPagination(pageable, query, countQuery);
  }

  private QBrandEnterpriseManagementVO getBrandEnterpriseVO() {
    return new QBrandEnterpriseManagementVO(
      brand.id,
      brand.brandName,
      brand.url,
      brand.representativeName,
      brand.representativeEmail,
      brand.activated
    );
  }

  private QBrandAccountManagementVO getBrandAccountVO() {
    return new QBrandAccountManagementVO(
      account.id,
      account.email,
      account.name,
      account.roleType,
      account.activated,
      account.recentSignInDateTime
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
