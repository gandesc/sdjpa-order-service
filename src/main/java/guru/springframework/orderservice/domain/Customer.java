package guru.springframework.orderservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer extends BaseEntity {

  @Size(max = 50)
  private String customerName;
  @Size(max = 20)
  private String phone;
  @Size(max = 255)
  @Email
  private String email;

  @Version
  private Integer version;

  @Valid
  @Embedded
  private Address address;

  @OneToMany(mappedBy = "customer")
  Set<OrderHeader> orders = new HashSet<>();
}
