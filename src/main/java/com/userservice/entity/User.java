package com.userservice.entity;

import com.userservice.enums.GenderEnum;
import com.userservice.listener.EntityListener;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@EntityListeners(EntityListener.class)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String birthDate;
    GenderEnum gender;
    String email;
    @Column(unique=true)
    String username;
    String password;
    String job;
    boolean isEnabled;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
}
