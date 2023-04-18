package com.superblog.factory;

import com.superblog.repository.jpa.PostEntity;

public class EntityObjectFactory {

    public final PostEntity POST = new PostEntity(
            null,
            "postId",
            "title",
            "content",
            null,
            null
    );

    public final PostEntity UPDATED_POST = new PostEntity(
            null,
            "postId",
            "updated title",
            "updated content",
            null,
            null
    );
}
