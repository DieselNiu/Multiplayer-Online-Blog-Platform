package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.BlogDao;
import com.github.DieselNiu.entity.Blog;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getBlogList(Integer page, Integer pageSize, Integer userId, Boolean atIndex) {
        return blogDao.getBlogList(page, pageSize, userId);
    }
}
