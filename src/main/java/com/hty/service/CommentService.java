package com.hty.service;

import com.hty.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    //根据作者名称获取评论
    Comment queryCommentByName(String comment_author);

    //获取所有评论
    List<Comment> queryAllComment();

    //获取同一个作者的全部评论
    List<Comment> queryCommentByAuthor(String comment_author);

    //获取同一个文章下的所有评论
    List<Comment> queryCommentByBlog(String comment_blog);

    //添加评论
    int addComment(Comment comment);

    //根据ID删除一个评论
    int deleteCommentByAuthor(String comment_id);
}
