package com.example.james.bean;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/2/1.
 */
public  class GenericBean<T> {
//    private Type mType;
//
//    public Type getGenericType() {
//        Type superclass = getClass().getGenericSuperclass();
//        mType = ((ParameterizedType) superclass).getActualTypeArguments()[0];
//        return mType;
//    }


    private final Type type;
    public static final Type LIST_STRING = (new GenericBean() {
    }).getType();

    protected GenericBean() {
        Type superClass = this.getClass().getGenericSuperclass();
        Log.e("lqx",superClass.toString());
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }
}


