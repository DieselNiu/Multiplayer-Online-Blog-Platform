package com.github.DieselNiu.dao;

import com.github.DieselNiu.entity.Blog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BlogDao {
    private final SqlSession sqlSession;

    @Inject
    public BlogDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Blog> getBlogList(Integer page, Integer pageSize, Integer userId) {
        int offset = (page - 1) * pageSize;
        Map<String, Object> param = new HashMap<>();
        param.put("offset", offset);
        param.put("limit", pageSize);
        param.put("userId", userId);
        return sqlSession.selectList("blogMapper.getBlogList", param);
    }

    public int getToTalBlogNum() {
        return sqlSession.selectOne("blogMapper.getTotalBlogNum");
    }
}
