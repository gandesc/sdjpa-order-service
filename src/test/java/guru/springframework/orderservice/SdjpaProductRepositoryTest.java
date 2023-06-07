package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.repositories.ProductRepository;
import guru.springframework.orderservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"guru.springframework.orderservice.services"})
public class SdjpaProductRepositoryTest {

  @Autowired
  ProductRepository productRepository;
  @Autowired
  ProductService productService;

  @Test
  void addAndUpdateProduct() {
    Product product = Product.builder()
        .description("New Product")
        .quantityOnHand(5)
        .productStatus(ProductStatus.NEW)
        .build();

    Product savedProduct = productRepository.save(product);

    Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

    assertThat(savedProduct2.getQuantityOnHand()).isEqualTo(25);
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
