package com.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.enums.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    String id;
    String name;
    LocalDate birthDate;
    GenderEnum gender;
    String email;
    String job;
    @JsonIgnore
    LocalDateTime createdDate;
    @JsonIgnore
    LocalDateTime lastModifiedDate;
}
