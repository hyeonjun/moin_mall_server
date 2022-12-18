package com.buying.back.application.account.domain;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.common.domain.Base;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "account",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
  },
  indexes = {
    @Index(columnList = "email"),
    @Index(columnList = "birthDay"),
    @Index(columnList = "signUpDateTime")
  }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_id")
  private Long accountId;

  @Setter
  @Column(name = "email", length = 191, nullable = false, unique = true)
  private String email;

  @Setter
  @Column(name = "name", length = 191, nullable = false)
  private String name;

  @Setter
  @Column(name = "password", length = 100, nullable = false)
  private String password;

  @Setter
  @Column(name = "activated", nullable = false)
  private boolean activated;

  private LocalDate birthDay;
  private LocalDateTime signUpDateTime;

  @Setter
  private LocalDateTime recentSignInDateTime;

  // TODO: 2022/12/18 비밀번호 변경한지 한달이 지난 경우 변경하라는 팝업 뜨도록 할 수 있게
  @Setter
  private LocalDateTime recentPasswordUpdateDateTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private RoleType roleType;

  @Enumerated(EnumType.STRING)
  @Column(name = "grade", nullable = false)
  private AccountGradeType gradeType;

  @Builder(builderClassName = "init", builderMethodName = "initAccount")
  public Account(CreateAccountDTO dto) {
    this.email = dto.getEmail();
    this.name = dto.getName();
    this.password = dto.getPassword();
    this.activated = true;
    this.birthDay = dto.getBirthDay();
    this.signUpDateTime = LocalDateTime.now();
    this.recentSignInDateTime = LocalDateTime.now();
    this.recentPasswordUpdateDateTime = LocalDateTime.now();
    this.roleType = RoleType.USER;
    this.gradeType = AccountGradeType.LV1;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account that = (Account) obj;
    return Objects.equals(getAccountId(), that.getAccountId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }
}
