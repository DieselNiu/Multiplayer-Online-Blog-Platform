package com.github.DieselNiu.Service;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
@Service
public class OrderService {

    private final UserService userService;

    @Inject
    public OrderService(UserService userService) {
        this.userService = userService;
    }
}
