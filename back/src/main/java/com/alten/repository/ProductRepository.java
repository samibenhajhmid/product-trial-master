package com.alten.repository;
import com.alten.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM products p WHERE p.quantity > 0")
    List<Product> findAllAvailableProducts();
}
