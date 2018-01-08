package com.example.yangjingan.myapplication;

import com.example.yangjingan.myapplication.PowerMockTest.ClassUnderTest;
import com.example.yangjingan.myapplication.PowerMockTest.IdGenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by yangjingan on 17-8-29.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class StaticTest {

    @Test
    public void testCallInternalInstance() throws Exception{
        PowerMockito.mockStatic(IdGenerator.class);
        PowerMockito.when(IdGenerator.generateNewId()).thenReturn(15L);
        ClassUnderTest underTest = new ClassUnderTest();
        Assert.assertEquals(14L,underTest.methodToTest());
        PowerMockito.verifyStatic();
    }

}
