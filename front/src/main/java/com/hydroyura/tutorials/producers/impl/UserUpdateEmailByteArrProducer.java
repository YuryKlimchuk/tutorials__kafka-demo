package com.hydroyura.tutorials.producers.impl;

import com.hydroyura.tutorials.producers.ByteArrProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateEmailByteArrProducer extends ByteArrProducer {


    protected UserUpdateEmailByteArrProducer(@Qualifier("topicUserUpdateEmail") NewTopic topic) {
        super(topic);
    }
}
