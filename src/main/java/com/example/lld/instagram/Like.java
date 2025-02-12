package com.example.lld.instagram;

import java.util.UUID;

public class Like {

    private final String id = UUID.randomUUID().toString();

    private String postId;
    private String liker;

    public Like(String postId, String liker) {
        this.postId = postId;
        this.liker = liker;
    }

    public String getPostId() {
        return postId;
    }

    public String getLiker() {
        return liker;
    }

    @Override
    public String toString() {
        return "Like{" +
                "postId='" + postId + '\'' +
                ", liker='" + liker + '\'' +
                '}';
    }
}
