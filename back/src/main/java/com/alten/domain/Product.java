package com.alten.domain;


import com.alten.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;


    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private Double rating;
    private Long createdAt;
    private Long updatedAt;
}
