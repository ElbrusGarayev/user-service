package com.userservice.service;

import com.userservice.enums.OrderStatusEnum;

public interface OrderService {

    void updateOrderStatus(OrderStatusEnum preStatus, OrderStatusEnum nextStatu);
}
