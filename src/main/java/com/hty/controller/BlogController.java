package com.hty.controller;

import com.hty.pojo.Blog;
import com.hty.pojo.Comment;
import com.hty.pojo.Tag;
import com.hty.pojo.User;
import com.hty.service.BlogService;
import com.hty.service.CommentService;
import com.hty.service.TagService;
import com.hty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    //返回首页显示
    @RequestMapping(value = "/toMain")
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
                blog.setBlog_content(substring+"...");
            }
        }

        List<Tag> tags = tagService.queryAllTag();
        model.addAttribute("blogs",blogs);
        model.addAttribute("tags",tags);
        return "main";
    }

    //显示某一篇博客的全部内容
    @RequestMapping(value = "/showBlog/{blog_id}")
    public String showBlog(@PathVariable("blog_id") String blog_id, Model model){
        Blog blog = blogService.queryBlogById(blog_id);
        List<Comment> comments = commentService.queryCommentByBlog(blog_id);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",comments);
        return "show_blog";
    }

    //给某一个博文添加评论
    @RequestMapping(value = "/addComment")
    public String addComment(@RequestParam("blog_id") String blog_id,
                             @RequestParam("comment_author") String comment_author,
                             @RequestParam("comment_content") String comment_content){
        System.out.println(comment_author);
        if(comment_author != null && comment_content != null && !"".equals(comment_author) && !"".equals(comment_content)){
            User user = userService.queryUserByName(comment_author);
            if(user == null){
                User newUser = new User();
                newUser.setUser_id(UUID.randomUUID().toString());
                newUser.setUser_name(comment_author);
                newUser.setUser_is_manager(0);
                newUser.setUser_create_time(new Date());
                newUser.setUser_blog_num(0);
                newUser.setUser_fans_num(0);
                newUser.setUser_focus_num(0);
                newUser.setUser_age(0);
                userService.addUser(newUser);
            }

            Comment comment = new Comment();
            comment.setComment_id(UUID.randomUUID().toString());
            comment.setComment_author(comment_author);
            comment.setComment_content(comment_content);
            comment.setComment_time(new Date());
            comment.setComment_blog(blog_id);
            commentService.addComment(comment);
        }
        return "redirect:/blog/showBlog/"+blog_id;
    }

}
