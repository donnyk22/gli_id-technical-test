package com.gli.id.services.order;

import com.gli.id.dtos.OrderDto;
import com.gli.id.dtos.forms.OrderAddForm;
import com.gli.id.dtos.forms.OrderEditForm;

import java.util.List;

public interface OrderService {
    List<OrderDto> find(Integer id);
    OrderDto add(OrderAddForm form);
    OrderDto edit(OrderEditForm form);
    OrderDto delete(Integer id);
}
