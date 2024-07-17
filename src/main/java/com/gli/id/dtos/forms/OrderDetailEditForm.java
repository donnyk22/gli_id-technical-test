package com.gli.id.dtos.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDetailEditForm extends OrderDetailAddForm{
    private Integer id;
}
