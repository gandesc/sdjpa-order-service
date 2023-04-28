package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class OrderLine extends BaseEntity {
  private Integer quantityOrdered;

  @ManyToOne
  private OrderHeader orderHeader;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OrderLine orderLine)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    if (!quantityOrdered.equals(orderLine.quantityOrdered)) {
      return false;
    }
    return orderHeader.equals(orderLine.orderHeader);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + quantityOrdered.hashCode();
    return result;
  }
}
