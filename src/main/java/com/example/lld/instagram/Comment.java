package com.example.lld.instagram;

import java.util.UUID;

public class Comment {

    private final String id = UUID.randomUUID().toString();

    private String content;

    private String postId;

    private String commenterId;

    public Comment(String content, String postId, String commenterId) {
        this.content = content;
        this.postId = postId;
        this.commenterId = commenterId;
    }

    public String getContent() {
        return content;
    }

    public String getPostId() {
        return postId;
    }

    public String getCommenterId() {
        return commenterId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", postId='" + postId + '\'' +
                ", commenterId='" + commenterId + '\'' +
                '}';
    }
}
