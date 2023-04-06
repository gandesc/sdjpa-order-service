package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SdjpaOrderHeaderRepositoryTest {
  @Autowired
  OrderHeaderRepository repository;

  @Test
  void testOrderSave() {
    OrderHeader entity = OrderHeader.builder().customerName("test").build();
    OrderHeader savedOrder = repository.save(entity);

    assertThat(savedOrder).isNotNull();
    assertThat(savedOrder.getId()).isNotNull();

    List<OrderHeader> found = repository.findAllByCustomerName("test");

    assertThat(found).isNotNull();
    assertThat(found.size()).isEqualTo(1);
  }

}
