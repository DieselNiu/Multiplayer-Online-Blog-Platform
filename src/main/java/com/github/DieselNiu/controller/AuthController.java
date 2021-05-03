package com.github.DieselNiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AuthController {
    @GetMapping("/auth")
    @ResponseBody
    public Object auth() {
        return new Result();
    }
    @PostMapping("/auth/login")
    public void login(@RequestBody Map<String,Object> usernameAndPasswordJson){
        System.out.println(usernameAndPasswordJson);
    }


    private static class Result {
        public String getStatus() {
            return "ok";
        }
        public boolean getIsLogin(){
             return false;
        }
    }
}