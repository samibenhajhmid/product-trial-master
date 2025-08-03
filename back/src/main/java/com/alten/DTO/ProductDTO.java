package com.alten.DTO;



public record ProductDTO(
        Long id,
        String code,
        String name,
        String description,
        String image,
        String category,
        Double price,
        Integer quantity,
        String internalReference,
        Long shellId,
        Double rating,
        Long createdAt,
        Long updatedAt
) { }