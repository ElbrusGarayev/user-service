package com.userservice.listener;

import com.userservice.entity.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class EntityListener {

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setLastModifiedDate(LocalDateTime.now());
    }
}
