package com.userservice.entity;

import com.userservice.enums.OrderStatusEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    OrderStatusEnum status;
    @OneToOne
    User user;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    ProductDetail productDetail;
    @OneToOne
    Card card;
}
