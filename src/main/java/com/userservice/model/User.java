package com.userservice.model;

import com.userservice.enums.GenderEnum;
import com.userservice.listener.UserListener;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@EntityListeners(value = UserListener.class)
@Table(name = "custom_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long id;
    String name;
    @Column(name = "birth_date")
    LocalDate birthDate;
    GenderEnum gender;
    String email;
    String password;
    String job;
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    LocalDateTime lastModifiedDate;
    boolean isEnabled;
}
