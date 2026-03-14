package com.hsf.sapotest.controller;

import com.hsf.sapotest.dto.request.OrderDetailCreateRequest;
import com.hsf.sapotest.dto.response.ResponseData;
import com.hsf.sapotest.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/flash-sale/order")
    public ResponseData orderSale(@RequestBody OrderDetailCreateRequest orderDetailCreateRequest) {

        saleService.createOrder(orderDetailCreateRequest);
        return new ResponseData(HttpStatus.CREATED.value(), "Tạo order thành công");
    }
}
