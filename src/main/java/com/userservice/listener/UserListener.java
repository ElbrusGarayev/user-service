package com.userservice.listener;

import com.userservice.model.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserListener extends AbstractMongoEventListener<User> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        User product = event.getSource();
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(LocalDateTime.now());
        } else {
            product.setLastModifiedDate(LocalDateTime.now());
        }
    }
}
