package com.github.DieselNiu.controller;

import com.github.DieselNiu.Service.UserService;
import com.github.DieselNiu.entity.AuthResult;
import com.github.DieselNiu.entity.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@Controller
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Inject
    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    @ResponseBody  // 将返回值json限定在responseBody里面
    public AuthResult auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.getUserByUsername(authentication == null ? null : authentication.getName());

        if (loggedInUser == null) {
            return AuthResult.notLoggedAuthResult();
        } else {
//            return loggedInUser;
            return AuthResult.loggedAuthResult(loggedInUser);
        }
    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Object logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User loggedInUser = this.userService.getUserByUsername(authentication == null ? null : authentication.getName());

        if (loggedInUser == null) {
            return AuthResult.failedResult("用户尚未登录");

        } else {
            SecurityContextHolder.clearContext();
            return AuthResult.logoutResult("注销成功");
        }
    }

    @PostMapping("/auth/register")
    @ResponseBody
    public Object register(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        if (username.length() < 1 || username.length() > 15) {
            return AuthResult.failedResult("invalid username");
        }
        if (password.length() < 6 || password.length() > 16) {
            return AuthResult.failedResult("invalid password");
        }
        try {
            userService.insertNewUser(username, password);
        } catch (DuplicateKeyException e) {
            return AuthResult.failedResult("username already exist");
        }
        login(usernameAndPassword);
        return AuthResult.successfulResult("注册成功", userService.getUserByUsername(username));
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username").toString();
        String password = usernameAndPassword.get("password").toString();
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username); //去自己的数据库拿到这个用户名的真正密码
        } catch (UsernameNotFoundException e) {
            return AuthResult.failedResult("用户不存在");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        // 把用户名和密码比对一下看这个人是不是要登录的这个人
        try {
            authenticationManager.authenticate(token); //进行鉴权

            SecurityContextHolder.getContext().setAuthentication(token); // 把用户信息保存在一个地方

            return AuthResult.successfulResult("登录成功", userService.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return AuthResult.failedResult("密码不正确");
        }
    }
}
