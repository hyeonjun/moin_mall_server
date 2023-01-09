package com.buying.back.application.inquiry.repository;

import com.buying.back.application.inquiry.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>,
  InquiryRepositoryCustom {


}
