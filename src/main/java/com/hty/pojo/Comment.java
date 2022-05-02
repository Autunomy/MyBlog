package com.hty.pojo;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class Comment {
    private String comment_author;
    private String comment_content;
    private Date comment_time;
    private String comment_id;
    private String comment_blog;
}
