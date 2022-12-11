package org.ecommerce.repository;

import org.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByProductId(String productId);
}
