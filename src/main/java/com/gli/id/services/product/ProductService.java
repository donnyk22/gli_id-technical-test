package com.gli.id.services.product;

import com.gli.id.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
}
