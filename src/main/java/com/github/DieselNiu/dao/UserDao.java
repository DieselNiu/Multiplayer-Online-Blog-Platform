package com.github.DieselNiu.dao;


import com.github.DieselNiu.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class UserDao {
    private final SqlSession sqlSession;

    @Inject
    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User getUserByUsername(String username) {
        return this.sqlSession.selectOne("myMapper.getUserByUsername", username);
    }

    public void insertNewUser(Map param) {
        this.sqlSession.insert("myMapper.insertNewUser", param);
    }
}

