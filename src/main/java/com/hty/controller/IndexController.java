package com.hty.controller;

import com.hty.pojo.Blog;
import com.hty.pojo.Tag;
import com.hty.service.BlogService;
import com.hty.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//这个Controller主要是用来设置欢迎页
@Controller
public class IndexController {
    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/")
    public String toMain(Model model){
        List<Blog> blogs = blogService.queryAllBlog();
        if(blogs.size() > 5){
            for(int i=6;i<blogs.size();++i){
                blogs.remove(i);
            }
        }

        for (Blog blog : blogs) {
            String blog_content = blog.getBlog_content();
            if(blog_content.length() > 150){
                String substring = blog_content.substring(0, 150);
                blog.setBlog_content(substring);
            }
        }

        List<Tag> tags = tagService.queryAllTag();
        model.addAttribute("blogs",blogs);
        model.addAttribute("tags",tags);
        return "main";
    }

    @RequestMapping(value = "/manage")
    public String manage(Model model){
        List<Blog> blogs = blogService.queryAllBlog();
        model.addAttribute("blogs",blogs);
        return "manage/show_all_blog";
    }
}
