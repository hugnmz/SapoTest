package com.hsf.sapotest.service;


import com.hsf.sapotest.dto.request.OrderDetailCreateRequest;

public interface SaleService {

    Long createOrder(OrderDetailCreateRequest orderDetailCreateRequest);
}
