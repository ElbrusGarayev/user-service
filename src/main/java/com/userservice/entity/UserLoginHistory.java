package com.userservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_login_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserLoginHistory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String accessToken;
    String ip;
    LocalDateTime loginDateTime;

    public UserLoginHistory(User user, String accessToken, String ip) {
        this.user = user;
        this.accessToken = accessToken;
        this.ip = ip;
        this.loginDateTime = LocalDateTime.now();
    }
}
