package com.gli.id.dtos.mappers;

import com.gli.id.dtos.OrderDetailDto;
import com.gli.id.dtos.OrderDto;
import com.gli.id.models.Order;
import com.gli.id.models.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static OrderDto toBaseDto(Order model){
        OrderDto orderDto = new OrderDto()
                .setUser_id(model.getUser_id())
                .setTotal_price(model.getTotal_price());
        orderDto.setId(model.getId());
        return orderDto;
    }

    public static OrderDto toDetailDto(Order model){
        OrderDto orderDto = toBaseDto(model);
        if (model.getUser_detail() != null){
            orderDto.setUser_data(UserMapper.toDto(model.getUser_detail()));
        }
        if (model.getOrder_list() != null && !model.getOrder_list().isEmpty()){
            List<OrderDetailDto> order_list = new ArrayList<>();
            for (OrderDetail order : model.getOrder_list()){
                order_list.add(OrderDetailMapper.toDto(order));
            }
            orderDto.setOrder_list(order_list);
        }
        return orderDto;

    }
}
