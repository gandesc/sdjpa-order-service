package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class OrderLine extends BaseEntity {
  private Integer quantityOrdered;

  @ManyToOne
  private OrderHeader orderHeader;

  @ManyToOne
  private Product product;

  @Version
  private Integer version;

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

    if (!Objects.equals(quantityOrdered, orderLine.quantityOrdered)) {
      return false;
    }
    if (!Objects.equals(orderHeader, orderLine.orderHeader)) {
      return false;
    }
    return Objects.equals(product, orderLine.product);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (quantityOrdered != null ? quantityOrdered.hashCode() : 0);
    result = 31 * result + (product != null ? product.hashCode() : 0);
    return result;
  }
}
