package com.github.DieselNiu.configuration;

import com.github.DieselNiu.Service.OrderService;
import com.github.DieselNiu.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfiguration {
    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(userService());
    }
}
