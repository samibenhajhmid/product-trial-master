package com.alten.service;

import com.alten.DTO.ProductDTO;


import java.util.List;
import java.util.Optional;


public interface ProductService {


   List<ProductDTO> getAllProducts();

     Optional<ProductDTO> getProductById(Long id);

     ProductDTO saveProduct(ProductDTO productDTO);

     void deleteProduct(Long id);
}
