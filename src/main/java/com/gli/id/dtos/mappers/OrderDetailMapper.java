package com.gli.id.dtos.mappers;

import com.gli.id.dtos.OrderDetailDto;
import com.gli.id.models.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDto toDto(OrderDetail model){
        OrderDetailDto orderDetailDto = new OrderDetailDto()
                .setOrder_id(model.getOrder_id())
                .setProduct_id(model.getProduct_id())
                .setQty(model.getQty());
        orderDetailDto.setId(model.getId());
        if (model.getProduct_detail() != null){
            orderDetailDto.setProduct_detail(ProductMapper.toDto(model.getProduct_detail()));
        }
        return orderDetailDto;
    }
}
