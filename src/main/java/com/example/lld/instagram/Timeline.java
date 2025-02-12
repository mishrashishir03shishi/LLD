package com.example.lld.instagram;

import java.util.*;

public class Timeline {

    private final String userId;

    private Date lastUpdatedAt;

    public Timeline(String userId) {
        this.userId = userId;
        this.lastUpdatedAt = Calendar.getInstance().getTime();
    }


    public List<Post> refreshTimeline(User user){
        List<Post> timelinePosts = new ArrayList<>();
        List<User> followingList = user.getFollowingList();
        for(User following: followingList){
            for(Post post: following.getPosts()){
                if(post.getPublishTime().after(lastUpdatedAt)){
                    timelinePosts.add(post);
                }
            }
        }
        Collections.sort(timelinePosts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                if(p1.getPublishTime().after(p2.getPublishTime())){
                    return 1;
                }
                else if(p1.getPublishTime().before(p2.getPublishTime())){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        this.lastUpdatedAt = Calendar.getInstance().getTime();
        return timelinePosts;
    }
}
