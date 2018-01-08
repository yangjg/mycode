package com.example.yangjingan.myapplication.PowerMockTest;

/**
 * Created by yangjingan on 17-8-29.
 */
public class ClassUnderTest {

    public long methodToTest() {
        final long id = IdGenerator.generateNewId();
        return id;
    }
}
