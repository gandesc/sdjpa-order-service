package guru.springframework.orderservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class OrderHeaderTest {

  @Test
  void testEquals() {
    OrderHeader oh1 = new OrderHeader();
    oh1.setId(1L);

    OrderHeader oh2 = new OrderHeader();
    oh2.setId(1L);

    assertThat(oh1).isEqualTo(oh2);
  }

  @Test
  void testNotEquals() {
    OrderHeader oh1 = new OrderHeader();
    oh1.setId(1L);

    OrderHeader oh2 = new OrderHeader();
    oh2.setId(3L);

    assertThat(oh1).isNotEqualTo(oh2);
  }
}