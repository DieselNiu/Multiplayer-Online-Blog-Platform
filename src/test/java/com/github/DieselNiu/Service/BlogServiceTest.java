package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.BlogDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock
    BlogDao blogdao;
    @InjectMocks
    BlogService blogService;



}
