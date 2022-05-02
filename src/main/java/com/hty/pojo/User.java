package com.hty.pojo;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class User {
    private String user_id;
    private String user_name;
    private String user_password;
    private Integer user_is_manager;
    private Date user_create_time;
    private Integer user_blog_num;
    private Integer user_fans_num;
    private Integer user_focus_num;
    private String user_email;
    private String user_phone;
    private Integer user_age;
}
