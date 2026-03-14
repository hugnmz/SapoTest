package com.hsf.sapotest.repository;

import com.hsf.sapotest.model.Order;
import com.hsf.sapotest.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
