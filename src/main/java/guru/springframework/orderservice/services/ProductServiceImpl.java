package guru.springframework.orderservice.services;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository repository;

  @Override
  public Product saveProduct(Product product) {
    return repository.saveAndFlush(product);
  }

  @Override
  public Product updateQOH(Long id, Integer quantityOnHand) {
    Product product = repository.findById(id).orElseThrow();

    product.setQuantityOnHand(quantityOnHand);

    return repository.saveAndFlush(product);
  }
}
