package com.hydroyura.tutorials.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.hydroyura.tutorials.consts.Topics.USER_CREATE;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder
                .name(USER_CREATE)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
