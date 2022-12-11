package org.ecommerce.repository;

import org.ecommerce.model.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Integer> {

    //public Optional<Discount> findByProductId(int productId);

}
