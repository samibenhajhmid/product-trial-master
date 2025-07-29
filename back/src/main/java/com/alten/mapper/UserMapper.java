package com.alten.mapper;

import com.alten.DTO.AccountRequest;
import com.alten.DTO.ProductDTO;
import com.alten.DTO.UserDTO;
import com.alten.domain.Product;
import com.alten.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDTO> {

    User toEntity(UserDTO user);

    UserDTO toDto(User user);
}
