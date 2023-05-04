package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category extends BaseEntity {
  private String description;

  @ManyToMany
  @JoinTable(name = "product_category",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Category category)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    return Objects.equals(description, category.description);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
