package com.gli.id.dtos.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDetailAddForm {
    @NotBlank(message = "Product ID may not be blank")
    private Integer product_id;
    @NotNull(message = "QTY may not be null")
    private Integer qty;
}
