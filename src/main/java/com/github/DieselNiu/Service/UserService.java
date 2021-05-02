package com.github.DieselNiu.Service;

public class UserService {
    public User getUserById(Integer id) {
        return new User(id, "");
    }
}
