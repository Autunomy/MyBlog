package com.hty.pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class Tag {
    private int tag_id;
    private String tag_name;

    public Tag(String tag_name){
        this.tag_name = tag_name;
    }
}
