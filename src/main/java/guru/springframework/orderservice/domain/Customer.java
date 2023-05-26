package guru.springframework.orderservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
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

  private String customerName;
  private String phone;
  private String email;

  @Version
  private Integer version;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "customer")
  Set<OrderHeader> orders = new HashSet<>();
}
