package com.hydroyura.tutorials.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.consts.Topics;
import com.hydroyura.tutorials.models.MsgContainer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonSerializer;

import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

@Configuration
public class ProducerConfig {

    @Bean
    ObjectMapper objectMapper() {
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    ProducerFactory<String, MsgContainer> producerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {

        var props = kafkaProperties.buildProducerProperties(null);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        var producerFactory = new DefaultKafkaProducerFactory<String, MsgContainer>(props);
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return producerFactory;
    }

    @Bean
    KafkaTemplate<String, MsgContainer> kafkaTemplate(ProducerFactory<String, MsgContainer> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    NewTopic topic() {
        return TopicBuilder
                .name(Topics.USER_CREATE)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
