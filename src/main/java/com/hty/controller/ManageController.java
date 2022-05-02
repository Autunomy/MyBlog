package com.hty.controller;

import com.hty.pojo.Blog;
import com.hty.pojo.Tag;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(value = "/manage")
public class ManageController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    //后台的首页应该是展示所有的博客信息
    @RequestMapping("/showAllBlog")
    public String show_all_blog(Model model){
        List<Blog> blogs = blogService.queryAllBlog();
        model.addAttribute("blogs",blogs);
        return "manage/show_all_blog";
    }

    //跳转到添加博客页面
    @RequestMapping("/toAddBlog")
    public String toAddBlog(Model model){
        List<Tag> tags = tagService.queryAllTag();
        model.addAttribute("tags",tags);
        return "manage/add_blog";
    }

    //添加博客页面上传的数据在这里处理，处理完成之后跳转到首页
    @RequestMapping("/addBlog")
    public String addBlog(@RequestParam("title") String title,
                          @RequestParam("tag") String tag,
//                          @RequestParam("blog") MultipartFile blog,
                          @RequestParam("text") String text){
        Blog newBlog = new Blog();
        InputStream inputStream;

        //两个都为空说明没有上传东西
//        if(blog == null && text == null){
//            //直接返回首页
//            return "redirect:/manage";
//        }
//
//        //如果有上传的文件
//        if(blog != null){
//            try {
//                inputStream = blog.getInputStream();
//                String blogContent = "";
//                byte[] bytes = new byte[1];
//                while(inputStream.read() != -1){
//                    inputStream.read(bytes);
//                    String s = bytes.toString();
//                    blogContent+=s;
//                }
//                //插入内容
//                newBlog.setBlog_content(blogContent);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //如果编辑器中有内容
        if(text != null){
            newBlog.setBlog_content(text);
        }

        //插入属性
        newBlog.setBlog_id(UUID.randomUUID().toString());
        newBlog.setBlog_name(title);
        newBlog.setBlog_author("Autunomy");
        newBlog.setBlog_date(new Date());
        newBlog.setBlog_watch_num(0);
        newBlog.setBlog_heart_num(0);
        newBlog.setBlog_tag(tag);
        blogService.addBlog(newBlog);
        return "redirect:/manage";
    }

    //跳转到修改博客内容
    @RequestMapping("/toUpdate/{blog_id}")
    public String toUpdate(@PathVariable("blog_id") String blog_id, Model model){
        Blog blog = blogService.queryBlogById(blog_id);
        List<Tag> tags = tagService.queryAllTag();
        model.addAttribute("blog",blog);
        model.addAttribute("tags",tags);
        return "manage/update_blog";
    }

    //修改博客内容
    @RequestMapping("/update")
    public String update(Blog blog){
        blogService.updateBlog(blog);
        return "redirect:/manage/";
    }

    //删除博客
    @RequestMapping("/deleteBlog/{blog_id}")
    public String deleteBlog(@PathVariable("blog_id") String blog_id){
        blogService.deleteBlogById(blog_id);
        return "redirect:/manage/";
    }

    //跳转到添加标签页面
    @RequestMapping("/toAddTag")
    public String toAddTag(){
        return "manage/add_tag";
    }

    //添加分类
    @RequestMapping("addTag")
    public String addTag(@RequestParam("tag_name") String tag_name){
        //在这里需要进行判断是否存在当前分类

        //产生随机数作为id
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        //将format中的-去掉
        String replace1 = format.replace("-", "");
        String replace2 = replace1.replace(" ", "");
        String replace3 = replace2.replace(":", "");
        String id = replace3.substring(0,9);
        Random random = new Random();
        random.setSeed(Integer.valueOf(id));

        //由于tag的id是一个整型，所以为了方便，就是用日期时间加上一个随机数
        Tag tag = new Tag(tag_name);
        tagService.addTag(tag);
        return "redirect:/manage/showAllTags";
    }

    //展示所有分类
    @RequestMapping("/showAllTags")
    public String showAllTags(Model model){
        List<Tag> tags = tagService.queryAllTag();
        model.addAttribute("tags",tags);
        return "manage/show_all_tags";
    }

    //跳转到分类修改页面
    @RequestMapping("/toUpdateTag/{tag_id}")
    public String toUpdateTag(@PathVariable("tag_id") String tag_id,Model model){
        Tag tag = tagService.queryById(tag_id);
        model.addAttribute("tag",tag);
        return "manage/update_tag";
    }

    //修改博客分类
    @RequestMapping("/updateTag")
    public String updateTag(Tag tag){
        tagService.updateTagById(tag);
        return "redirect:/manage/showAllTags";
    }


    //删除博客分类
    @RequestMapping("/deleteTag/{tag_id}")
    public String deleteTag(@PathVariable("tag_id") String tag_id){
        tagService.deleteTagById(tag_id);
        return "redirect:/manage/showAllTags";
    }
}
