package com.hsf.sapotest.repository;

import com.hsf.sapotest.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select sum(od.quantity) from OrderDetail od where od.order.user.userId = :userId and od.product" +
            ".productId = :productId ")
    Integer sumQuantityPurchased(@Param("userId") Long userId,@Param("productId") Long productId);
}
