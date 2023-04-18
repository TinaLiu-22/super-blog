package com.superblog.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {

    private String postId;
    private String title;
    private String content;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;

}
