package guru.springframework.orderservice.boostrap;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.repositories.CustomerRepository;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import guru.springframework.orderservice.services.ProductService;
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

  @Autowired
  ProductService productService;

  public void updateProduct() {
    Product product = Product.builder()
        .description("New Product")
        .quantityOnHand(5)
        .productStatus(ProductStatus.NEW)
        .build();

    Product savedProduct = productService.saveProduct(product);

    Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

    System.out.println(savedProduct2.getQuantityOnHand());
  }

  @Override
  public void run(String... args) throws Exception {
    updateProduct();

    service.readOrderData();

    Customer customer = Customer.builder()
        .customerName("Testing Version")
        .build();

    Customer customer1 = repository.save(customer);
    System.out.println("Version is: " + customer.getVersion());

    customer1.setCustomerName("Testing Version 2");
    Customer customer2 = repository.save(customer1);
    System.out.println(customer2.getVersion());

    customer2.setCustomerName("Testing Version 3");
    Customer customer3 = repository.save(customer2);
    System.out.println(customer3.getVersion());

    repository.delete(customer3);
  }
}
