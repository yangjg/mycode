package com.example.yjq.androidlearn.cglib;

/**
 * Created by yangjingan on 2016/9/21.
 */
public class ForumServiceImpl implements ForumService {

    @NeedTest(true)
    public void removeTopic(int topicId) {

        System.out.println("模拟删除Topic记录:"+topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @NeedTest(false)
    public void removeForum(int forumId) {

        System.out.println("模拟删除Forum记录:"+forumId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
