package com.buying.back.application.common.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
public class Base {

  @CreationTimestamp
  protected LocalDateTime createdDateTime;
  @UpdateTimestamp
  protected LocalDateTime updatedDateTime;

}
