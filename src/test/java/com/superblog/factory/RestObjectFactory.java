package com.superblog.factory;

import com.superblog.controller.generated.api.rest.model.Post;

public class RestObjectFactory {

    public final Post POST = new Post()
            .postId(TestFactory.POST_ID)
            .title("title")
            .content("content")
            .dateCreated(null)
            .dateModified(null);

    public final Post UPDATED_POST = new Post()
            .postId(TestFactory.POST_ID)
            .title("updated title")
            .content("updated content")
            .dateCreated(null)
            .dateModified(null);
}
