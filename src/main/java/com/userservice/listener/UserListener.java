package com.userservice.listener;

import com.userservice.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class UserListener {

    @PrePersist
    public void prePersist(User user) {
        user.setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(User user) {
        user.setLastModifiedDate(LocalDateTime.now());
    }
}
