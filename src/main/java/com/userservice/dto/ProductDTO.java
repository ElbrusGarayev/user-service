package com.userservice.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "ProductDTO")
public class ProductDTO {

    String id;
    String name;
    String description;
    int price;
    String brand;
    String size;
    int stockCount;
    String type;
    String color;
    String gender;
}
