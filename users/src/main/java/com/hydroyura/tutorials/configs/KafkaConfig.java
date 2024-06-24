package com.hydroyura.tutorials.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.models.MsgContainer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import static com.hydroyura.tutorials.consts.Topics.USER_CREATE;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaConfig {


    @Bean
    ObjectMapper objectMapper() {
        return JacksonUtils.enhancedObjectMapper();
    }


    @Bean
    ConsumerFactory<String, MsgContainer> consumerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        var props = kafkaProperties.buildConsumerProperties(null);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put("TYPE_MAPPING", "com.hydroyura.tutorials.models.MsgContainer");
        props.put(MAX_POLL_RECORDS_CONFIG, 3);
        props.put(MAX_POLL_INTERVAL_MS_CONFIG, 3_000);

        var consumerFactory = new DefaultKafkaConsumerFactory<String, MsgContainer>(props);
        consumerFactory.setValueDeserializer(new JsonDeserializer<>(objectMapper));
        return consumerFactory;
    }


    @Bean
    NewTopic newTopic() {
        return TopicBuilder
                .name(USER_CREATE)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
