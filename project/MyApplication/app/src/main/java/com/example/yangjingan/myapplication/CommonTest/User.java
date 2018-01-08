package com.example.yangjingan.myapplication.CommonTest;

/**
 * Created by yangjingan on 17-5-18.
 */
public class User {

    private static String USER_ID = getUserId();

    public User(String id){
        this.USER_ID = id;
    }
    private static String getUserId() {
        throw new RuntimeException("UserId Not found");
    }
}
