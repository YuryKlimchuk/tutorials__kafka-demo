package com.hydroyura.tutorials.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.models.MsgContainer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.ProjectingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import static com.hydroyura.tutorials.consts.Topics.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

@Configuration
public class KafkaConfig {

    @Bean
    ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        var props = kafkaProperties.buildConsumerProperties(null);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(MAX_POLL_RECORDS_CONFIG, 3);
        props.put(MAX_POLL_INTERVAL_MS_CONFIG, 3_000);

        var consumerFactory = new DefaultKafkaConsumerFactory<String, String>(props);
        return consumerFactory;
    }

    @Bean
    KafkaListenerContainerFactory<?> listenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.setBatchListener(false);
        factory.setRecordMessageConverter(new StringJsonMessageConverter());
        return factory;
    }

//    @Bean
//    MessageListenerContainer messageListenerContainer(KafkaListenerContainerFactory<?> kafkaListenerContainerFactory) {
//        ContainerProperties containerProps = new ContainerProperties("topic1", "topic2");
//        return new KafkaMessageListenerContainer<String, String>(kafkaListenerContainerFactory,containerProps );
//    }


    @Bean
    NewTopic newTopic() {
        return TopicBuilder
                .name(USER_UPDATE_EMAIL)
                .partitions(2)
                .replicas(1)
                .build();
    }

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

}
