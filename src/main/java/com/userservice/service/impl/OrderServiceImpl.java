package com.userservice.service.impl;

import com.userservice.entity.Order;
import com.userservice.enums.OrderStatusEnum;
import com.userservice.repository.OrderRepository;
import com.userservice.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
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
            log.info("Previous: " + order);
            order.setStatus(nextStatus);
            log.info("Next: " + order);
            orderRepository.save(order);
        });
    }
}
