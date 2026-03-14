package com.hsf.sapotest.service.impl;

import com.hsf.sapotest.dto.request.OrderDetailCreateRequest;
import com.hsf.sapotest.exception.InvalidDataException;
import com.hsf.sapotest.model.Order;
import com.hsf.sapotest.model.OrderDetail;
import com.hsf.sapotest.model.Product;
import com.hsf.sapotest.repository.OrderDetailRepository;
import com.hsf.sapotest.repository.OrderRepository;
import com.hsf.sapotest.repository.ProductRepository;
import com.hsf.sapotest.repository.UserRepository;
import com.hsf.sapotest.service.SaleService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleSerivceImpl implements SaleService {

    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public Long createOrder(OrderDetailCreateRequest orderDetailCreateRequest) {

        Product product =
                productRepository.findById(orderDetailCreateRequest.getProductId()).orElseThrow(() -> new InvalidDataException("Sản phẩm này không tồn tại"));

        if(product.getInStock() < orderDetailCreateRequest.getQuantity()) {
            throw new InvalidDataException("Số lượng mua không được lớn hơn số lượng tồn kho");
        }

        Integer hasPurchased = orderDetailRepository.sumQuantityPurchased(orderDetailCreateRequest.getUserId(),
                orderDetailCreateRequest.getProductId()) != null ?
                orderDetailRepository.sumQuantityPurchased(orderDetailCreateRequest.getUserId(), orderDetailCreateRequest.getProductId()) : 0;

        if( hasPurchased + orderDetailCreateRequest.getQuantity() > 2) {
            throw new InvalidDataException("Bạn hết lượt mua sản phẩm này");
        }

        Order order = new Order();
                order.setUser(userRepository.findById(orderDetailCreateRequest.getUserId()).orElseThrow(() -> new InvalidDataException("user không tồn tại")));

        Order ordered = orderRepository.save(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailCreateRequest.getQuantity());
        orderDetail.setOrder(ordered);
        orderDetailRepository.save(orderDetail);

        product.setInStock(product.getInStock() - orderDetailCreateRequest.getQuantity());
        productRepository.save(product);
        return ordered.getOrderId();
    }
}
