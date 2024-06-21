package com.hydroyura.tutorials.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hydroyura.tutorials.consts.KafkaConsts.USER_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(USER_TOPIC, 1, (short) 1);
    }

}
