package guru.springframework.orderservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer extends BaseEntity {

  @Length(max = 50)
  private String customerName;
  @Length(max = 20)
  private String phone;
  private String email;

  @Version
  private Integer version;

  @Valid
  @Embedded
  private Address address;

  @OneToMany(mappedBy = "customer")
  Set<OrderHeader> orders = new HashSet<>();
}
