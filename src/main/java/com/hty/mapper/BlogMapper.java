package com.hty.mapper;

import com.hty.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {
    //根据ID获取博客
    Blog queryBlogById(String blog_id);

    //根据文章标题获取博客
    Blog queryBlogByName(String blog_name);

    //获取所有博客
    List<Blog> queryAllBlog();

    //获取同一个作者的全部博客
    List<Blog> queryBlogByAuthor(String blog_author);

    //添加一个博客
    int addBlog(Blog blog);

    //根据ID删除一个博客
    int deleteBlogById(String blog_id);

    //根据名称删除一个博客
    int deleteBlogByName(String blog_name);

    //修改博客信息
    int updateBlog(Blog blog);
}
