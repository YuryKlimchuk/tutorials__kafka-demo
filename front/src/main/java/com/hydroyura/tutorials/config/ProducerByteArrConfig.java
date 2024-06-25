package com.hydroyura.tutorials.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.consts.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

@Configuration
public class ProducerByteArrConfig {

    @Bean
    ProducerFactory<String, byte[]> producerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        var props = kafkaProperties.buildProducerProperties(null);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);

        var producerFactory = new DefaultKafkaProducerFactory<String, byte[]>(props);
        producerFactory.setValueSerializer(new ByteArraySerializer());

        return producerFactory;
    }

    @Bean
    KafkaTemplate<String, byte[]> kafkaTemplate(ProducerFactory<String, byte[]> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    NewTopic topicUserUpdateEmail() {
        return TopicBuilder
                .name(Topics.USER_UPDATE_EMAIL)
                .partitions(2)
                .replicas(1)
                .build();
    }


}
