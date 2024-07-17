package com.gli.id.services.product;

import com.gli.id.dtos.ProductDto;
import com.gli.id.dtos.exceptions.BusinessException;
import com.gli.id.dtos.mappers.ProductMapper;
import com.gli.id.models.Product;
import com.gli.id.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        Iterable<Product> products = productRepository.findAll();
        if (!products.iterator().hasNext()) throw new BusinessException("Product is empty");
        return StreamSupport
                .stream(products.spliterator(), false)
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}
