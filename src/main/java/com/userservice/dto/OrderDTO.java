package com.userservice.dto;

import com.userservice.enums.OrderStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderDTO {

    OrderStatusEnum status;
    UserDTO userDTO;
    CardDTO cardDTO;
    ProductDetailDTO productDetailDTO;
}
