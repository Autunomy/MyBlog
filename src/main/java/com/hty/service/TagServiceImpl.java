package com.hty.service;

import com.hty.mapper.TagMapper;
import com.hty.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagMapper tagMapper;

    @Override
    public Tag queryById(String tag_id) {
        return tagMapper.queryById(tag_id);
    }

    @Override
    public List<Tag> queryAllTag() {
        return tagMapper.queryAllTag();
    }

    @Override
    public Tag queryByName(String tag_name) {
        return queryByName(tag_name);
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public int deleteTagById(String tag_id) {
        return tagMapper.deleteTagById(tag_id);
    }

    @Override
    public int deleteTagName(String tag_name) {
        return tagMapper.deleteTagName(tag_name);
    }

    @Override
    public int updateTagById(Tag tag) {
        return tagMapper.updateTagById(tag);
    }
}
