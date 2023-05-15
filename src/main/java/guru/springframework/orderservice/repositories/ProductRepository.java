package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByDescription(String description);
  Optional<Product> findByDescription(String description);
}
