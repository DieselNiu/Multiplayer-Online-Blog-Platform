package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.BlogDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
}
