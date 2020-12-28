package com.userservice.service.impl;

import com.userservice.entity.Order;
import com.userservice.enums.OrderStatusEnum;
import com.userservice.repository.OrderRepository;
import com.userservice.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    @Override
    public void updateOrderStatus(OrderStatusEnum preStatus, OrderStatusEnum nextStatus) {
        List<Order> orders = orderRepository
                .findAll()
                .stream()
                .filter(order -> order
                        .getStatus()
                        .equals(preStatus))
                .collect(Collectors.toList());
        orders.forEach(order -> {
            order.setStatus(nextStatus);
            orderRepository.save(order);
        });
    }
}
