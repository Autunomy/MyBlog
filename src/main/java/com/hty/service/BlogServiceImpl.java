package com.hty.service;

import com.hty.mapper.BlogMapper;
import com.hty.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog queryBlogById(String blog_id) {
        return blogMapper.queryBlogById(blog_id);
    }

    @Override
    public Blog queryBlogByName(String blog_name) {
        return blogMapper.queryBlogByName(blog_name);
    }

    @Override
    public List<Blog> queryAllBlog() {
        return blogMapper.queryAllBlog();
    }

    @Override
    public List<Blog> queryBlogByAuthor(String blog_author) {
        return blogMapper.queryBlogByAuthor(blog_author);
    }

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    @Override
    public int deleteBlogById(String blog_id) {
        return blogMapper.deleteBlogById(blog_id);
    }

    @Override
    public int deleteBlogByName(String blog_name) {
        return blogMapper.deleteBlogByName(blog_name);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }
}
