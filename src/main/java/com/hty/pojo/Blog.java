package com.hty.pojo;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class Blog {
    private String blog_id;
    private String blog_name;
    private String blog_content;
    private String blog_author;
    private Date blog_date;
    private Integer blog_watch_num;
    private Integer blog_heart_num;
    private String blog_tag;
}
