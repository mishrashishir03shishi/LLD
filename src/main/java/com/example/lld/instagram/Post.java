package com.example.lld.instagram;

import java.util.*;

public class Post {

    private final String id = UUID.randomUUID().toString();

    private String postContent;

    private final String userId;

    final List<Like> likeList = new ArrayList<>();

    final List<Comment> commentList = new ArrayList<>();

    Date publishTime;

    public Post(String postContent, String userId) {
        this.postContent = postContent;
        this.userId = userId;
        this.publishTime = Calendar.getInstance().getTime();
    }

    void likePost(String userId){
        boolean alreadyLiked = false;
        for(Like like: this.likeList){
            if(like.getLiker().equals(userId)){
                alreadyLiked = true;
                break;
            }
        }
        if(!alreadyLiked){
            Like like = new Like(this.id, userId);
            likeList.add(like);
        }
    }

    void addComment(String userId, String content){
        Comment comment = new Comment(content, this.id, userId);
        commentList.add(comment);
    }

    int getLikes(){
        return likeList.size();
    }

    List<Comment> getComments(){
        return commentList;
    }

    public void editPost(String content){
        this.postContent = content;
        this.publishTime = Calendar.getInstance().getTime();
    }

    public void view(){
        System.out.println("Posting user : " + userId);
        System.out.println("Post content : " + postContent);
        System.out.println("Likes : " + likeList.size());
        for(Like like : likeList){
            System.out.println("Liker : " + like.getLiker());
        }
        System.out.println("Comments : " + commentList.size());
        for(Comment comment : commentList){
            System.out.println("Commenter : " + comment.getCommenterId());
            System.out.println("Comment : " + comment.getContent());
        }
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postContent='" + postContent + '\'' +
                ", userId='" + userId + '\'' +
                ", like=" + likeList.size() +
                ", commentList=" + commentList +
                ", publishTime=" + publishTime +
                '}';
    }
}
