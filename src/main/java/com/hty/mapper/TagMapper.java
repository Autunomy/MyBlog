package com.hty.mapper;

import com.hty.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    //根据id获取分类
    Tag queryById(String tag_id);

    //根据名称查询分类
    Tag queryByName(String tag_name);

    //获取全部分类
    List<Tag> queryAllTag();

    //增加一个分类
    int addTag(Tag tag);

    //根据id删除一个分类
    int deleteTagById(String tag_id);

    //根据名称删除一个分类
    int deleteTagName(String tag_name);

    //根据id修改一个分类
    int updateTagById(Tag tag);
}
