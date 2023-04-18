package com.superblog.factory;

import com.superblog.service.pojo.PostModel;

public class ModelObjectFactory {

    public final PostModel POST = new PostModel(
            TestFactory.POST_ID,
            "title",
            "content",
            null,
            null
    );

    public final PostModel UPDATED_POST = new PostModel(
            TestFactory.POST_ID,
            "updated title",
            "updated content",
            null,
            null
    );
}
