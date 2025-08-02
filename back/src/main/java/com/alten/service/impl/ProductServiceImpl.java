package com.alten.service.impl;


import com.alten.DTO.ProductDTO;
import com.alten.domain.Product;
import com.alten.mapper.ProductMapper;
import com.alten.repository.ProductRepository;
import com.alten.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Cacheable(value = "items")
    public List<ProductDTO> getAllProducts() {


        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
