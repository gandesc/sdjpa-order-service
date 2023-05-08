package guru.springframework.orderservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends BaseEntity {

  private String customerName;
  private String phone;
  private String email;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "customer")
  Set<OrderHeader> orders = new HashSet<>();
}
