package com.gli.id.dtos.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderAddForm {
    @NotBlank(message = "User ID may not be blank")
    private Integer user_id;
    private List<OrderDetailAddForm> order_list;
}
