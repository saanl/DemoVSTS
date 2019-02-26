package com.vsts.willl.demovsts;

import android.util.Log;

public class TestForError {

    public void error1(){
        String s = null;
        Log.e("sssss",s.length()+"");
    }

    public void error2(){
        String[] ss = {};
        Log.e("sssss",ss[2]+"");
    }

    public void error3(){
        String[] ss = {};
        Log.e("sssss",ss[2]+"");
    }

}
