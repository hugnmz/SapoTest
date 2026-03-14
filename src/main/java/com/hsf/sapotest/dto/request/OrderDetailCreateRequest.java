package com.hsf.sapotest.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailCreateRequest {

    private Long productId;
    private Integer quantity;
    private Long userId;
}
