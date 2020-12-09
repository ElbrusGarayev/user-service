package com.userservice.entity;

import com.userservice.listener.EntityListener;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
}
