package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.BlogDao;
import com.github.DieselNiu.entity.Blog;
import com.github.DieselNiu.entity.BlogListResult;
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

    public BlogListResult getBlogList(Integer page, Integer pageSize, Integer userId, Boolean atIndex) {
        try {
            int totalBlogNum = blogDao.getToTalBlogNum();
            int totalPage = totalBlogNum % pageSize == 0 ? totalBlogNum / pageSize : totalBlogNum / pageSize + 1;
            List<Blog> blogList = blogDao.getBlogList(page, pageSize, userId);
            return BlogListResult.successfulBlogListResult("获取成功", totalBlogNum, page, totalPage, blogList);
        } catch (Exception e) {
            // throw new RuntimeException(e);
            return BlogListResult.failBlogResult("系统异常");
        }

    }
}