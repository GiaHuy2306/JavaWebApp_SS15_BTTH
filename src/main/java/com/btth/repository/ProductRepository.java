package com.btth.repository;

import com.btth.model.Product;
import com.btth.projection.ProductView;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends
        JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    List<Product> findByCategoryAndStatus(String category, Boolean status);

    @Query("SELECT p FROM Product p WHERE p.status = true ORDER BY p.price DESC")
    Page<Product> findHotProducts(Pageable pageable);

    Page<ProductView> findByStatusTrue(Pageable pageable);
}
