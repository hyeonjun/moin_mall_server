package com.buying.back.application.account.domain;

import com.buying.back.application.common.domain.Base;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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

  @Column(name = "email", length = 191, nullable = false, unique = true)
  private String email;

  @Setter
  private String name;

  @Setter
  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @Setter
  @Column(name = "activated", nullable = false)
  private boolean activated;

  private LocalDateTime birthDay;
  private LocalDateTime signUpDateTime;

  @Setter
  private LocalDateTime recentSignInDateTime;

  @OneToMany(mappedBy = "account")
  private List<Role> roles = new ArrayList<>();

  @Builder
  private Account(Long accountId, String email, String name, String password, boolean activated,
    LocalDateTime birthDay, LocalDateTime signUpDateTime, LocalDateTime recentSignInDateTime,
    List<Role> roles) {
    this.accountId = accountId;
    this.email = email;
    this.name = name;
    this.password = password;
    this.activated = activated;
    this.birthDay = birthDay;
    this.signUpDateTime = signUpDateTime;
    this.recentSignInDateTime = recentSignInDateTime;
    this.roles = roles;
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
