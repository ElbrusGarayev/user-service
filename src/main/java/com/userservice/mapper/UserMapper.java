package com.userservice.mapper;

import com.userservice.dto.*;
import com.userservice.entity.Card;
import com.userservice.entity.Order;
import com.userservice.entity.ProductDetail;
import com.userservice.entity.User;
import feign.Response;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO modelToDto(User user);

    @Mapping(target = "userDTO", source = "order.user")
    @Mapping(target = "cardDTO", source = "order.card")
    @Mapping(target = "productDetailDTO", source = "order.productDetail")
    OrderDTO modelToDto(Order order);

    CardDTO modelToDto(Card card);

    @Mapping(target = "id", ignore = true)
    ProductDetail dtoToModel(ProductDetailDTO productDetailDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "orderDTO.userDTO")
    @Mapping(target = "card", source = "orderDTO.cardDTO")
    @Mapping(target = "productDetail", source = "orderDTO.productDetailDTO")
    Order dtoToModel(OrderDTO orderDTO);

    @Mapping(target = "createdDate", ignore = true)
    User dtoToModel(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User dtoToModel(UserDTO userDTO, @MappingTarget User user);
}
