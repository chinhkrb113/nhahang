package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Blog;
import com.example.nhahang.entity.Product;
import com.example.nhahang.model.request.CreateBlogRequest;

public interface BlogService {
    
    List<Blog> getList();

    List<Blog> getListNewest(int limit);

    Blog getBlog(long id);

    Blog createBlog(CreateBlogRequest request);

    Blog updateBlog(long id,CreateBlogRequest request);

    void deleteBlog(long id);
    List<Blog> getListBlogByUser(long id);

}
