package com.buying.back.application.inquiry.repository;

import static com.buying.back.application.account.domain.QAccount.account;
import static com.buying.back.application.inquiry.domain.QInquiry.inquiry;

import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.inquiry.service.vo.QInquiryVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class InquiryRepositoryImpl extends CustomQuerydslRepositorySupport
  implements InquiryRepositoryCustom {

  public InquiryRepositoryImpl() {
    super(Inquiry.class);
  }

  @Override
  public Page<InquiryVO> findAllByAccount(Pageable pageable, Long accountId) {

    JPAQuery<InquiryVO> query = select(getInquiryVO())
      .from(inquiry)
      .where(inquiry.author.id.eq(accountId))
      .orderBy(inquiry.id.desc());

    JPAQuery<Long> countQuery = select(inquiry.count())
      .from(inquiry)
      .where(inquiry.author.id.eq(accountId));

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
      inquiry.content,
      inquiry.inquiryParentType,
      inquiry.inquiryChildType
    );
  }
}
