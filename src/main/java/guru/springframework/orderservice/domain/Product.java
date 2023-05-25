package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends BaseEntity {
  private String description;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

  @ManyToMany
  @JoinTable(name = "product_category",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product)) return false;
    if (!super.equals(o)) return false;

    Product product = (Product) o;

    if (getDescription() != null ? !getDescription().equals(product.getDescription()) : product.getDescription() != null)
      return false;
    return getProductStatus() == product.getProductStatus();
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
    result = 31 * result + (getProductStatus() != null ? getProductStatus().hashCode() : 0);
    return result;
  }
}
