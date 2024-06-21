package com.hydroyura.tutorials.producers;

import com.hydroyura.tutorials.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.hydroyura.tutorials.consts.KafkaConsts.USER_TOPIC;

@Service
public class UserCreateProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String user) {
        kafkaTemplate.send(USER_TOPIC, "CREATE_NEW", user);
    }

}
