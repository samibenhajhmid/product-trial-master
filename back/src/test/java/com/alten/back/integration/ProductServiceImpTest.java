package com.alten.back.integration;


import com.alten.DTO.ProductDTO;
import com.alten.domain.Product;
import com.alten.repository.ProductRepository;
import com.alten.service.ProductService;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Data
public class ProductServiceImpTest {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductService productService;

    @Test
    void testSaveProduct() {
        ProductDTO productDTO = new ProductDTO(
                null,
                "CODE123",
                "Clavier mécanique",
                "Clavier mécanique RGB",
                "image-url",
                "Accessoires",
                89.99,
                10,
                "REF-001",
                123L,
                4.5,
                System.currentTimeMillis(),
                System.currentTimeMillis()
        );

        ProductDTO saved = productService.saveProduct(productDTO);

        assertNotNull(saved.id());
        assertEquals("Clavier mécanique", saved.name());
    }



}
