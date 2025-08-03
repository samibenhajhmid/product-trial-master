package com.alten.mapper;

import com.alten.DTO.ProductDTO;
import com.alten.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<Product, ProductDTO> {



    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO productDTO);

}
