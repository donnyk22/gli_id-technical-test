package com.gli.id.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class ProductDto {
    private Integer id;
    private String product_name;
    private Date expired_date;
    private Integer price;
}
