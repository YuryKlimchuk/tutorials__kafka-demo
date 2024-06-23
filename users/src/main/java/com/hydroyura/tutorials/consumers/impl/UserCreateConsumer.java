package com.hydroyura.tutorials.consumers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.consts.Topics;
import com.hydroyura.tutorials.consumers.BaseConsumer;
import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.services.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.hydroyura.tutorials.consts.Topics.USER_CREATE;

@Service
public class UserCreateConsumer extends BaseConsumer {

    @Autowired
    private UserService userService;


    private ObjectMapper objectMapper = new ObjectMapper();

    //@Override
    @KafkaListener(topics = USER_CREATE, groupId = "TEST_GROUP_USER")
    protected void consume(ConsumerRecord<String, String> msg) throws IOException {
        LOG.info("Got msg from kafka = [{}]", msg.toString());
        User user = objectMapper.readValue(msg.value(), User.class);
        userService.save(user);
    }
}
