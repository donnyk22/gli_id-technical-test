package com.gli.id.dtos.mappers;

import com.gli.id.dtos.ProductDto;
import com.gli.id.models.Product;

public class ProductMapper {
    public static ProductDto toDto(Product model){
        ProductDto productDto = new ProductDto()
                .setProduct_name(model.getProduct_name())
                .setPrice(model.getPrice())
                .setExpired_date(model.getExpired_date());
        productDto.setId(model.getId());
        return productDto;
    }
}
