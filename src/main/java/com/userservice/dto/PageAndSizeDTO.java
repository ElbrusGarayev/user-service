package com.userservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageAndSizeDTO {

    @NotNull(message = "page_cannot_be_empty")
    int page;
    @NotNull(message = "size_cannot_be_empty")
    int size;
}
