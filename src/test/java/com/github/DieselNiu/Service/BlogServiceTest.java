package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.BlogDao;
import com.github.DieselNiu.entity.BlogListResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock
    BlogDao blogdao;
    @InjectMocks
    BlogService blogService;

    @Test
    void testSuccessfulGetBlogList() {
        blogService.getBlogList(1, 10, null, true);
        Mockito.verify(blogdao).getBlogList(1, 10, null);
    }

    @Test
    void testFailGetBlogList() {
        Mockito.when(blogdao.getBlogList(1, 10, null)).thenThrow(new RuntimeException("System Error!"));
        BlogListResult blogListResult = blogService.getBlogList(1, 10, null, true);
        Assertions.assertTrue(blogListResult.getMsg().contains("系统异常"));
    }


}
