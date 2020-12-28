package com.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.userservice.enums.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;
    @NotBlank(message = "name_is_required")
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    @NotNull(message = "gender_is_required")
    GenderEnum gender;
    @NotBlank(message = "email_is_required")
    @Email(regexp = "^(.+)@(.+)$", message = "invalid_email_pattern")
    String email;
    @NotBlank(message = "username_is_required")
    String username;
    @NotBlank(message = "password_is_required")
    @Size(min = 8, message = "password_length_shouldn't_be_less_than_8")
    String password;
    @NotBlank(message = "job_is_required")
    String job;
    @JsonIgnore
    LocalDateTime createdDate;
    @JsonIgnore
    LocalDateTime lastModifiedDate;
    @JsonIgnore
    boolean isEnabled;
}
