package guru.springframework.orderservice.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class Address {
  private String address;
  @Length(max = 20)
  private String city;
  private String state;
  private String zipCode;
}
