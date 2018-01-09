// forService.aidl
package com.example.yjq.androidlearn;
import com.example.yjq.androidlearn.forActivity;
// Declare any non-default types here with import statements

interface forService {
   void registCall(forActivity cb);
   void invokCallBack();
   int getLog();
}
