package com.gli.id.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDto extends BaseDto{
    private Integer user_id;
    private Integer total_price;
    private List<OrderDetailDto> order_list;
    private UserDto user_data;
}
