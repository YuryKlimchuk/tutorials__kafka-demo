package com.hydroyura.tutorials.models;

public class MsgContainer<T> {

    private T content;
    private Class<T> clazz;


    public MsgContainer() {}


    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
