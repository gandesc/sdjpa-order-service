package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
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

  @Test
  void testSaveOrderWithLine() {
    OrderHeader entity = OrderHeader.builder().customerName("New Customer").build();

    OrderLine orderLine = OrderLine.builder().quantityOrdered(5).build();

    entity.setOrderLines(Set.of(orderLine));
//    orderLine.setOrderHeader(entity);

    OrderHeader savedOrder = repository.save(entity);

    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getId()).isNotNull();

    OrderHeader fetchedOrder = repository.findById(savedOrder.getId()).orElse(null);

    assertThat(fetchedOrder).isNotNull();
    assertThat(fetchedOrder.getId()).isNotNull();
    assertThat(fetchedOrder.getCreatedDate()).isNotNull();
    assertThat(fetchedOrder.getLastModifiedDate()).isNotNull();
    assertEquals(fetchedOrder.getOrderLines().size(), 1);
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
