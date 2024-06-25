package com.hydroyura.tutorials.producers;

public interface BaseProducer<T> {

    void send(T msg);

}
