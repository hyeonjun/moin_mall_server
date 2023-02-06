package com.buying.back.application.account.domain;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.common.domain.Base;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
  private Long id;

  @Column(name = "email", length = 191, nullable = false)
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

  @Setter
  private LocalDate birthDay;
  private LocalDateTime signUpDateTime;

  @Setter
  private LocalDateTime recentSignInDateTime;

  // TODO: 2022/12/18 비밀번호 변경한지 한달이 지난 경우 변경하라는 팝업 뜨도록 할 수 있게
  @Setter
  private LocalDateTime recentPasswordUpdateDateTime;

  @ManyToOne
  @JoinColumn(name = "account_brand_id", referencedColumnName = "brand_id")
  @JsonBackReference
  private Brand brand;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", length = 191, nullable = false)
  private RoleType roleType;

  @Enumerated(EnumType.STRING)
  @Column(name = "grade", length = 191)
  private AccountGradeType gradeType;

  @Builder(builderClassName = "initNormal", builderMethodName = "initAccount")
  public Account(CreateAccountDTO dto) {
    this.email = dto.getEmail();
    this.name = dto.getName();
    this.password = dto.getPassword();
    this.activated = true;
    this.birthDay = dto.getBirthDay();
    this.signUpDateTime = LocalDateTime.now();
    this.recentSignInDateTime = LocalDateTime.now();
    this.recentPasswordUpdateDateTime = LocalDateTime.now();
    this.roleType = RoleType.NORMAL;
    this.gradeType = AccountGradeType.LV1;
  }

  @Builder(builderClassName = "test", builderMethodName = "testAccount")
  public Account(Long id, String email, String name, String password,
                 RoleType roleType) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.password = password;
    this.birthDay = LocalDate.now();
    this.signUpDateTime = LocalDateTime.now();
    this.recentSignInDateTime = LocalDateTime.now();
    this.recentPasswordUpdateDateTime = LocalDateTime.now();
    this.roleType = roleType;
  }

  @Builder(builderClassName = "initBrandAccount" , builderMethodName = "initBrandAccount")
  public Account(CreateBrandDTO dto, Brand brand) {
    this.brand = brand;
    this.email = dto.getEmail();
    this.name = dto.getName();
    this.password = dto.getPassword();
    this.activated = true;
    this.signUpDateTime = LocalDateTime.now();
    this.recentSignInDateTime = LocalDateTime.now();
    this.recentPasswordUpdateDateTime = null;
    this.roleType = dto.getRoleType();
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
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
