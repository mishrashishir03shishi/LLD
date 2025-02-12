package com.example.lld.instagram;

import java.util.ArrayList;
import java.util.List;

public class Instagram {

    private final List<User> userList = new ArrayList<>();

    private static Instagram instance;

    private static Instagram getInstance(){
        if(instance==null){
            instance = new Instagram();
        }
        return instance;
    }

    public static void run(){

        Instagram instagram = getInstance();

        User user1 = new User("Michael");
        User user2 = new User("Shaun");
        User user3 = new User("Peter");
        User user4 = new User("Jacob");

        instagram.signUpUser(user1);
        instagram.signUpUser(user2);
        instagram.signUpUser(user3);
        instagram.signUpUser(user4);

        //make the follower followee list

        user1.addFollower(user2);
        user1.addFollower(user3);

        user1.followUser(user2);
        user1.followUser(user4);

        user2.addFollower(user3);

        user2.followUser(user4);

        user3.addFollower(user1);
        user3.addFollower(user4);

        user3.followUser(user4);


        user4.followUser(user2);

        //start creating posts
        user1.createPost("1st post from " + user1.getName());
        user4.createPost("1st post from " + user4.getName());
        user2.createPost("1nd post from " + user2.getName());
        user2.createPost("2nd post from " + user2.getName());
        user3.createPost("1st post from " + user3.getName());

        //View timeline
        List<Post> timeLinePostsUser3 = user3.getTimeline();

        System.out.println("Displaying user3 aka Peter's timeline");

        //add Comment on each post
        for(Post p : timeLinePostsUser3){
            p.view();
            user3.likePost(p);
            user3.addComment(p, "Posting comment from user : " + user3.getName());
        }

        List<Post> timeLinePostsUser1 = user1.getTimeline();

        System.out.println("Displaying user1 aka Michael's timeline");

        //add Comment on each post
        for(Post p : timeLinePostsUser1){
            p.view();
        }
    }

    public void signUpUser(User user){
        userList.add(user);
    }

    public User getUser(User user){
        return userList.stream().filter(u -> u.equals(user)).findFirst().orElseThrow();
    }
}
