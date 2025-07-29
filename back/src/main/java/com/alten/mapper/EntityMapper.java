package com.alten.mapper;



public interface EntityMapper<E,D> {

    E toEntity(D dto);
    D toDto(E entity);
}
