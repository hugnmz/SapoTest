package com.hsf.sapotest.repository;

import com.hsf.sapotest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p.inStock from Product p where p.productId =:productId")
    Integer findInStockByProductId(@Param("productId") Long productId);


}
