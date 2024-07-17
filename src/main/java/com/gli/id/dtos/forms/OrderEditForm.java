package com.gli.id.dtos.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderEditForm {
    @NotBlank(message = "ID may not be blank")
    private Integer id;
    @NotBlank(message = "User ID may not be blank")
    private Integer user_id;
    private List<OrderDetailEditForm> order_list;
}
