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
  void addAndUpdateProduct() {
    Product product = Product.builder()
        .description("New Product")
        .quantityOnHand(5)
        .productStatus(ProductStatus.NEW)
        .build();

    Product savedProduct = productRepository.save(product);

    savedProduct.setQuantityOnHand(15);

    Product savedProduct2 = productRepository.saveAndFlush(savedProduct);

    assertThat(savedProduct2.getQuantityOnHand()).isEqualTo(15);
  }

  @Test
  void testGetCategory() {
    Product product = productRepository.findByDescription("PRODUCT1").get();

    assertThat(product).isNotNull();
    assertThat(product.getCategories().size()).isEqualTo(2);
  }

  @Test
  void testSaveProduct() {
    Product savedProduct = Product.builder()
        .description("new")
        .productStatus(ProductStatus.NEW)
        .build();

    savedProduct = productRepository.save(savedProduct);

    Product fetchedProduct = productRepository.getReferenceById(savedProduct.getId());

    assertThat(fetchedProduct).isNotNull();
    assertThat(fetchedProduct.getDescription()).isEqualTo(savedProduct.getDescription());
    assertThat(fetchedProduct.getProductStatus()).isEqualTo(savedProduct.getProductStatus());
  }
}
