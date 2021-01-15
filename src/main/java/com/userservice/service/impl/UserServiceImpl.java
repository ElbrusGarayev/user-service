package com.userservice.service.impl;

import com.userservice.client.ProductClient;
import com.userservice.dto.*;
import com.userservice.entity.Card;
import com.userservice.entity.Order;
import com.userservice.entity.User;
import com.userservice.enums.OrderStatusEnum;
import com.userservice.exception.CardNotFoundException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.jwt.JwtUser;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.CardRepository;
import com.userservice.repository.OrderRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;
    final UserRepository userRepository;
    final ProductClient productClient;
    final OrderRepository orderRepository;
    final CardRepository cardRepository;

    @Override
    public List<UserDTO> getAll(PageAndSizeDTO pageAndSizeDTO) {
        Pageable pageable = PageRequest.of(pageAndSizeDTO.getPage(), pageAndSizeDTO.getSize());
        Page<User> page = userRepository.findAll(pageable);
        List<User> users = page.getContent();
        return users.stream().map(userMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        User newUser = userMapper.dtoToModel(userDTO, user);
        return userMapper.modelToDto(userRepository.save(newUser));
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAll();
    }

    @Override
    public OrderDTO purchaseProduct(OrderBodyDTO orderBodyDTO, Authentication auth) {
        ProductDetailDTO productDetailDTO = productClient.purchaseProduct(orderBodyDTO);
        User user = userRepository.findById(((JwtUser) auth.getPrincipal()).getId())
                .orElseThrow(UserNotFoundException::new);
        Card card = cardRepository
                .findByCardNumberAndCvvAndExpDate(
                        orderBodyDTO.getCardDTO().getCardNumber(),
                        orderBodyDTO.getCardDTO().getCvv(),
                        orderBodyDTO.getCardDTO().getExpDate())
                .orElseThrow(CardNotFoundException::new);
        OrderDTO orderDTO = OrderDTO.builder()
                .cardDTO(userMapper.modelToDto(card))
                .productDetailDTO(productDetailDTO)
                .userDTO(userMapper.modelToDto(user))
                .status(OrderStatusEnum.WAITING)
                .build();
        Order order = orderRepository.save(userMapper.dtoToModel(orderDTO));
        return mask(order);
    }

    private OrderDTO mask(Order order){
        String originalCardNumber = order.getCard().getCardNumber();
        String maskedCardNumber = originalCardNumber.substring(0, 4) + "********" + originalCardNumber.substring(12);
        order.getCard().setCardNumber(maskedCardNumber);
        return userMapper.modelToDto(order);
    }
}
