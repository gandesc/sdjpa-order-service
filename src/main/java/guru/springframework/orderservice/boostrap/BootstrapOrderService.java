package guru.springframework.orderservice.boostrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapOrderService {

  @Autowired
  private OrderHeaderRepository repository;

  @Transactional
  public void readOrderData() {
    OrderHeader orderHeader = repository.findById(1L).get();

    orderHeader.getOrderLines().forEach(ol -> {
      System.out.println(ol.getProduct().getDescription());

      ol.getProduct().getCategories().forEach(cat -> {
        System.out.println(cat.getDescription());
      });
    });
  }
}
