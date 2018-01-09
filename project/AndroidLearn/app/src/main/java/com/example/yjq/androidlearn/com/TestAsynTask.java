package com.example.yjq.androidlearn.com;

/**
 * Created by yjq on 2016/6/30.
 */
public abstract class TestAsynTask<A,B,C> {


    private A processResult(B data){

        return  doWork(data);
    }

    public  abstract A doWork(B... pa);

}

