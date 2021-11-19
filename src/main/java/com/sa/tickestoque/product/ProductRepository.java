package com.sa.tickestoque.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);
}
