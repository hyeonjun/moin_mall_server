package com.buying.back.application.inquiry.repository;

import static com.buying.back.application.account.domain.QAccount.account;
import static com.buying.back.application.inquiry.domain.QInquiry.inquiry;

import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.repository.param.SearchInquiryListParam;
import com.buying.back.application.inquiry.service.vo.InquiryManagementVO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.inquiry.service.vo.QInquiryManagementVO;
import com.buying.back.application.inquiry.service.vo.QInquiryVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import java.time.LocalTime;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public class InquiryRepositoryImpl extends CustomQuerydslRepositorySupport
  implements InquiryRepositoryCustom {

  public InquiryRepositoryImpl() {
    super(Inquiry.class);
  }

  @Override
  public Page<InquiryVO> findAllByAccount(Pageable pageable, Long accountId) {

    JPAQuery<InquiryVO> query = select(getInquiryVO())
      .from(inquiry)
      .innerJoin(inquiry.author, account)
      .where(account.id.eq(accountId)
        .and(inquiry.deleted.isFalse()))
      .orderBy(inquiry.id.desc());

    JPAQuery<Long> countQuery = select(inquiry.count())
      .from(inquiry)
      .innerJoin(inquiry.author, account)
      .where(account.id.eq(accountId)
        .and(inquiry.deleted.isFalse()));

    return applyPagination(pageable, query, countQuery);
  }

  @Override
  public Page<InquiryManagementVO> findAllByManagement(Pageable pageable, SearchInquiryListParam param) {
    BooleanBuilder whereCondition = getInquiryWhereCondition(param);

    JPAQuery<InquiryManagementVO> query = select(getInquiryManagementVO())
      .from(inquiry)
      .innerJoin(inquiry.author, account)
      .where(whereCondition)
      .orderBy(inquiry.id.desc());

    JPAQuery<Long> countQuery = select(inquiry.count())
      .from(inquiry)
      .innerJoin(inquiry.author, account)
      .where(whereCondition)
      .orderBy(inquiry.id.desc());

    return applyPagination(pageable, query, countQuery);
  }

  private QInquiryVO getInquiryVO() {
    return new QInquiryVO(
      account.id,
      account.name,
      inquiry.id,
      inquiry.createdDateTime,
      inquiry.updatedDateTime,
      inquiry.title,
      inquiry.inquiryParentType,
      inquiry.answer.isNotNull()
    );
  }

  private QInquiryManagementVO getInquiryManagementVO() {
    return new QInquiryManagementVO(
      account.id,
      account.name,
      inquiry.id,
      inquiry.createdDateTime,
      inquiry.updatedDateTime,
      inquiry.title,
      inquiry.inquiryParentType,
      inquiry.answer.isNotNull(),
      inquiry.deleted
    );
  }

  private BooleanBuilder getInquiryWhereCondition(SearchInquiryListParam param) {
    BooleanBuilder whereCondition = new BooleanBuilder();

    if (Objects.nonNull(param.getInquiryId())) {
      whereCondition.and(inquiry.id.eq(param.getInquiryId()));
    }

    if (Objects.nonNull(param.getCreatedDateFrom())) {
      whereCondition.and(inquiry.createdDateTime.goe(
        param.getCreatedDateFrom().atTime(LocalTime.MIN)));
    }

    if (Objects.nonNull(param.getCreatedDateTo())) {
      whereCondition.and(inquiry.createdDateTime.lt(
        param.getCreatedDateTo().plusDays(1).atTime(LocalTime.MIN)));
    }

    if (Objects.nonNull(param.getDeleted())) {
      whereCondition.and(inquiry.deleted.eq(param.getDeleted()));
    }

    if (Boolean.TRUE.equals(param.getReplied())) {
      whereCondition.and(inquiry.answer.isNotNull());
    } else {
      whereCondition.and(inquiry.answer.isNull());
    }

    if (Objects.nonNull(param.getInquiryParentType())) {
      whereCondition.and(inquiry.inquiryParentType.eq(param.getInquiryParentType().getParentCode()));
    }

    if (Objects.nonNull(param.getInquiryChildType())) {
      whereCondition.and(inquiry.inquiryChildType.eq(param.getInquiryChildType().getChildCode()));
    }

    if (Objects.nonNull(param.getAuthorId())) {
      whereCondition.and(account.id.eq(param.getAuthorId()));
    }

    if (StringUtils.hasText(param.getAuthorEmail())) {
      whereCondition.and(account.email.contains(param.getAuthorEmail()));
    }

    return whereCondition;
  }
}
