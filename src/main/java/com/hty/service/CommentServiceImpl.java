package com.hty.service;

import com.hty.mapper.CommentMapper;
import com.hty.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment queryCommentByName(String comment_author) {
        return commentMapper.queryCommentByName(comment_author);
    }

    @Override
    public List<Comment> queryAllComment() {
        return commentMapper.queryAllComment();
    }

    @Override
    public List<Comment> queryCommentByAuthor(String comment_author) {
        return commentMapper.queryCommentByAuthor(comment_author);
    }

    @Override
    public List<Comment> queryCommentByBlog(String comment_blog) {
        return commentMapper.queryCommentByBlog(comment_blog);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int deleteCommentByAuthor(String comment_id) {
        return commentMapper.deleteCommentByAuthor(comment_id);
    }
}
