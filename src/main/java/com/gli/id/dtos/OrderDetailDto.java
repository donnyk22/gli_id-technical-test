package com.gli.id.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDetailDto extends BaseDto{
    private Integer order_id;
    private Integer product_id;
    private Integer qty;
    private ProductDto product_detail;
}
