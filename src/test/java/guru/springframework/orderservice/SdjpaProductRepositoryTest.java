package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SdjpaProductRepositoryTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  void testSaveProduct() {
    Product product = Product.builder()
        .description("new")
        .productStatus(ProductStatus.NEW)
        .build();

    product = productRepository.save(product);

    Product fetched = productRepository.getReferenceById(product.getId());

    assertThat(fetched).isNotNull();
    assertThat(fetched.getDescription()).isEqualTo(product.getDescription());
    assertThat(fetched.getProductStatus()).isEqualTo(product.getProductStatus());
  }
}
