package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.Address;
import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.repositories.CustomerRepository;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import guru.springframework.orderservice.repositories.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {
  private final static String PRODUCT_D1 = "Product 1";
  private final static String PRODUCT_D2 = "Product 2";
  private final static String PRODUCT_D3 = "Product 3";
  private final static String TEST_CUSTOMER = "TEST CUSTOMER";

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  OrderHeaderRepository orderHeaderRepository;

  @Disabled
  @Rollback(value = false)
  @Test
  void testDataLoader() {
    List<Product> products = loadProducts();
    Customer customer = loadCustomers();
    
    int ordersToCreate = 20000;
    
    for (int i=0; i< ordersToCreate; i++) {
      System.out.println("Creating order #: " + i);
      saveOrder(customer, products);
    }

    orderHeaderRepository.flush();
  }

  private OrderHeader saveOrder(Customer customer, List<Product> products) {
    Random random = new Random();

    OrderHeader orderHeader = new OrderHeader();
    orderHeader.setCustomer(customer);

    products.forEach(product -> {
      OrderLine orderLine = OrderLine.builder()
          .product(product)
          .quantityOrdered(random.nextInt(20))
          .build();

//      orderHeader.getOrderLines().add(orderLine);
      orderHeader.addOrderLine(orderLine);
    });

    return orderHeaderRepository.save(orderHeader);
  }

  private Customer loadCustomers() {
    return getOrSaveCustomer(TEST_CUSTOMER);
  }

  private Customer getOrSaveCustomer(String name) {
    return customerRepository.findCustomerByCustomerNameIgnoreCase(name)
        .orElseGet(() -> {
          Address address = Address.builder()
              .address("123 Main")
              .city("New Orleans")
              .state("LA")
              .build();

          Customer c1 = Customer.builder()
              .customerName(name)
              .email("test@example.com")
              .address(address)
              .build();

          return customerRepository.save(c1);
        });
  }

  private List<Product> loadProducts() {
    List<Product> products = new ArrayList<>();
    products.add(getOrSaveProduct(PRODUCT_D1));
    products.add(getOrSaveProduct(PRODUCT_D2));
    products.add(getOrSaveProduct(PRODUCT_D3));

    return products;
  }

  private Product getOrSaveProduct(String description) {
    return productRepository.findByDescription(description)
        .orElseGet(() -> {
          Product p1 = Product.builder()
              .description(description)
              .productStatus(ProductStatus.NEW)
              .build();

          return  productRepository.save(p1);
        });
  }
}
