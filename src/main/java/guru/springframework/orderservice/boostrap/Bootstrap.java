package guru.springframework.orderservice.boostrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bootstrap implements CommandLineRunner {

  @Autowired
  private OrderHeaderRepository repository;

  @Transactional
  @Override
  public void run(String... args) throws Exception {
    OrderHeader orderHeader = repository.findById(1L).get();

    orderHeader.getOrderLines().forEach(ol -> {
      System.out.println(ol.getProduct().getDescription());

      ol.getProduct().getCategories().forEach(cat -> {
        System.out.println(cat.getDescription());
      });
    });
  }
}
