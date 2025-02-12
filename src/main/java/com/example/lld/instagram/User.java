package com.example.lld.instagram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final String id = UUID.randomUUID().toString();

    private String name;

    private final List<User> followerList = new ArrayList<>();

    private final List<User> followingList = new ArrayList<>();

    private final List<Post> posts = new ArrayList<>();

    private final Timeline timeline;

    public User(String name) {
        this.name = name;
        this.timeline = new Timeline(this.id);
    }

    public String getName() {
        return name;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public List<User> getFollowingList() {
        return followingList;
    }

    public List<Post> getPosts() {
        return posts;
    }


    public void addFollower(User user){
        if(this.equals(user)){
            throw new RuntimeException("Can't add same person to follower list");
        }
        boolean followerAlreadyPresent = false;
        for(User follower: this.followerList){
            if(user.equals(follower)){
                followerAlreadyPresent = true;
                break;
            }
        }
        if(!followerAlreadyPresent){
            this.followerList.add(user);
            user.getFollowingList().add(this);
        }
    }


    public void followUser(User user){
        if(this.equals(user)){
            throw new RuntimeException("Can't add same person to following list");
        }
        boolean alreadyFollowing = false;
        for(User follower: this.followingList){
            if(user.equals(follower)){
                alreadyFollowing = true;
                break;
            }
        }
        if(!alreadyFollowing){
            this.followingList.add(user);
            user.getFollowerList().add(this);
        }
    }


    public void createPost(String postContent){
        Post post = new Post(postContent, this.id);
        this.posts.add(post);
    }

    public void editPost(String postId, String content){
        Post post = null;
        for(Post tempPost: this.getPosts()){
            if(tempPost.getId().equals(postId)){
                post = tempPost;
                break;
            }
        }
        post.editPost(content);
    }



    public List<Post> getTimeline() {
        return this.timeline.refreshTimeline(this);
    }


    public void likePost(Post p){
        p.likePost(this.id);
    }

    public void addComment(Post p, String comment){
        p.getComments().add(new Comment(comment, p.getId(), this.id));
    }
}
