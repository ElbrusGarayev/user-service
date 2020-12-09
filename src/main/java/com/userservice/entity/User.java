package com.userservice.entity;

import com.userservice.enums.GenderEnum;
import com.userservice.listener.EntityListener;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@EntityListeners(EntityListener.class)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate birthDate;
    GenderEnum gender;
    String email;
    @Column(unique=true)
    String username;
    String password;
    String job;
    boolean isEnabled;
}
