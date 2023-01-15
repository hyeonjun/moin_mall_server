package com.buying.back.application.inquiry.domain;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.common.domain.Base;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.UpdateInquiryDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "inquiry",
  indexes = {
    @Index(columnList = "inquiry_parent_type"),
    @Index(columnList = "inquiry_parent_type, inquiry_child_type")
  }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "inquiry_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "inquiry_author_id", nullable = false, referencedColumnName = "account_id")
  @JsonBackReference
  private Account author;

  @Column(name = "inquiry_parent_type", length = 191, nullable = false)
  private String inquiryParentType;
  @Column(name = "inquiry_child_type", length = 191, nullable = false)
  private String inquiryChildType;

  // TODO: 2023/01/08 주문번호 필드 추가

  @Setter
  @Column(nullable = false)
  private String title;

  @Setter
  @Column(name = "content", length = 1000,nullable = false)
  private String content;

  @Setter
  private boolean deleted;

  // TODO: 2023/01/08 사진 관련 Entity 추가 후 매핑
  // TODO: 2023/01/08 답변 엔티티를 따로 만들어서 할 것인지?

  @Builder(builderClassName = "init", builderMethodName = "initInquiry")
  public Inquiry(CreateInquiryDTO dto, Account author) {
    this.author = author;
    this.inquiryParentType = dto.getInquiryParentType().getParentCode();
    this.inquiryChildType = dto.getInquiryChildType().getChildCode();
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

  public void updateInquiry(UpdateInquiryDTO dto) {
    this.inquiryParentType = dto.getInquiryParentType().getParentCode();
    this.inquiryChildType = dto.getInquiryChildType().getChildCode();
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Inquiry)) {
      return false;
    }
    Inquiry that = (Inquiry) obj;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
