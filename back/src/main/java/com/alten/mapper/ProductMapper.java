package com.alten.mapper;

import com.alten.DTO.ProductDTO;
import com.alten.domain.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<Product, ProductDTO> {

    @Override
    ProductDTO toDto(Product product);

    @Override
    Product toEntity(ProductDTO productDTO);
}
