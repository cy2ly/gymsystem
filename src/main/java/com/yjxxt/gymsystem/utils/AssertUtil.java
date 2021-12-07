package com.yjxxt.gymsystem.utils;


import com.yjxxt.gymsystem.exceptions.ParamsException;

public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
