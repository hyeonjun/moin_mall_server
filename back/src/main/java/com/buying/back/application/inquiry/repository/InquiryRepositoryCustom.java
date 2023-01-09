package com.buying.back.application.inquiry.repository;

import com.buying.back.application.inquiry.service.vo.InquiryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryRepositoryCustom {

  Page<InquiryVO> findAllByAccount(Pageable pageable, Long accountId);

}
