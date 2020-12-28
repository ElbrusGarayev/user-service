package com.userservice.schedule;

import com.userservice.enums.OrderStatusEnum;
import com.userservice.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderScheduler {

    final OrderService orderService;

    @Scheduled(fixedRate = 15000)
    public void registerOrder() {
        log.info("The status of the orders are updating...");
        orderService.updateOrderStatus(OrderStatusEnum.WAITING, OrderStatusEnum.REGISTERED);
        log.info("The status of the orders has been updated!");
    }

    @Scheduled(fixedRate = 25000)
    public void sendOrder() {
        log.info("The status of the orders are updating...");
        orderService.updateOrderStatus(OrderStatusEnum.REGISTERED, OrderStatusEnum.SENDED);
        log.info("The status of the orders has been updated!");
    }

    @Scheduled(fixedRate = 45000)
    public void finishOrder() {
        log.info("The status of the orders are updating...");
        orderService.updateOrderStatus(OrderStatusEnum.SENDED, OrderStatusEnum.DONE);
        log.info("The status of the orders has been updated!");
    }
}
