package com.hsf.sapotest.controller;

import com.hsf.sapotest.dto.request.OrderDetailCreateRequest;
import com.hsf.sapotest.dto.response.ResponseData;
import com.hsf.sapotest.model.Product;
import com.hsf.sapotest.repository.ProductRepository;
import com.hsf.sapotest.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SaleController {

    private final SaleService saleService;
    private final ProductRepository productRepository;

    @GetMapping("/flash-sale")
    public String flashSaleUI() {
        return "flash-sale-ui";
    }

    @GetMapping("/api/flash-sale/products")
    @ResponseBody
    public ResponseData<List<Product>> getProducts() {
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách thành công", productRepository.findAll());
    }

    @PostMapping("/api/flash-sale/order")
    @ResponseBody
    public ResponseData orderSale(@RequestBody OrderDetailCreateRequest orderDetailCreateRequest) {
        saleService.createOrder(orderDetailCreateRequest);
        return new ResponseData(HttpStatus.CREATED.value(), "Tạo order thành công");
    }
}
