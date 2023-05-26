package guru.springframework.orderservice.boostrap;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.repositories.CustomerRepository;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bootstrap implements CommandLineRunner {

  @Autowired
  CustomerRepository repository;

  @Autowired
  private BootstrapOrderService service;

  @Override
  public void run(String... args) throws Exception {
    service.readOrderData();

    Customer customer = Customer.builder().customerName("new").build();

    repository.save(customer);

    System.out.println(customer.getVersion());
    repository.delete(customer);
  }
}
