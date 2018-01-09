package com.example.yjq.androidlearn.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by yjq on 2016/8/22.
 */
public class MyEditViewInput extends EditText {
    public MyEditViewInput(Context context) {
        super(context);
    }

    public MyEditViewInput(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditViewInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
      //  return super.onCreateInputConnection(outAttrs);
        return  new MyInputConnection(super.onCreateInputConnection(outAttrs),false);
    }

    public class MyInputConnection extends InputConnectionWrapper{

        public MyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            if(text.toString().contains("j")){
                return  false;
            }
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if(event.getKeyCode()== KeyEvent.KEYCODE_DEL){
                return false;
            }
            return super.sendKeyEvent(event);
        }
    }
}
