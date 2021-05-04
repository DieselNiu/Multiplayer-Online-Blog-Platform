package com.github.DieselNiu.Service;

import com.github.DieselNiu.entity.User;
import com.github.DieselNiu.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserService {
    private UserMapper userMapper;

    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(Integer id) {
       return userMapper.findUserById(id);
    }
}
