package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByDescription(String description);
  Optional<Product> findByDescription(String description);

  @Override
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Product> findById(Long aLong);
}
