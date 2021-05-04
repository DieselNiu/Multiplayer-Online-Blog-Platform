package com.github.DieselNiu;

import com.github.DieselNiu.Service.UserService;
import com.github.DieselNiu.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public User index() {
        return this.userService.getUserById(1);
    }


}
