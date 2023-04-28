package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import guru.springframework.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SdjpaOrderHeaderRepositoryTest {
  @Autowired
  OrderHeaderRepository repository;

  @Autowired
  ProductRepository productRepository;

  Product product;

  @BeforeEach
  void setUp() {
    product = Product.builder()
        .productStatus(ProductStatus.NEW)
        .description("test product")
        .build();

    productRepository.save(product);
  }

  @Test
  void testSaveOrderWithLine() {
    OrderHeader orderHeader = OrderHeader.builder().customerName("New Customer").build();

    OrderLine orderLine = OrderLine.builder().product(product).quantityOrdered(5).build();

    orderHeader.addOrderLine(orderLine);

    OrderHeader savedOrder = repository.save(orderHeader);

    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getId()).isNotNull();

    OrderHeader fetchedOrder = repository.findById(savedOrder.getId()).orElse(null);

    assertThat(fetchedOrder).isNotNull();
    assertThat(fetchedOrder.getId()).isNotNull();
    assertThat(fetchedOrder.getCreatedDate()).isNotNull();
    assertThat(fetchedOrder.getLastModifiedDate()).isNotNull();
    assertEquals(fetchedOrder.getOrderLines().size(), 1);
    assertThat(fetchedOrder.getOrderLines().stream().findFirst().get().getProduct()).isNotNull();
  }
  @Test
  void testSaveOrder() {
    OrderHeader entity = OrderHeader.builder().customerName("New Customer").build();
    OrderHeader savedOrder = repository.save(entity);

    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getId()).isNotNull();

    OrderHeader fetchedOrder = repository.findById(savedOrder.getId()).orElse(null);

    assertThat(fetchedOrder).isNotNull();
    assertThat(fetchedOrder.getId()).isNotNull();
    assertThat(fetchedOrder.getCreatedDate()).isNotNull();
    assertThat(fetchedOrder.getLastModifiedDate()).isNotNull();
  }

  @Test
  void testFindAllByName() {
    OrderHeader entity = OrderHeader.builder().customerName("test").build();
    OrderHeader savedOrder = repository.save(entity);

    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getId()).isNotNull();

    List<OrderHeader> found = repository.findAllByCustomerName("test");

    assertThat(found).isNotNull();
    assertThat(found.size()).isEqualTo(1);
  }

}
