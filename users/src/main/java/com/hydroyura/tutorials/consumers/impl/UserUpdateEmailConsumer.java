package com.hydroyura.tutorials.consumers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.consumers.BaseConsumer;
import com.hydroyura.tutorials.models.events.UserChangeEmail;
import com.hydroyura.tutorials.services.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.hydroyura.tutorials.consts.Topics.USER_UPDATE_EMAIL;

@Service
public class UserUpdateEmailConsumer extends BaseConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topicPartitions = @TopicPartition(topic = USER_UPDATE_EMAIL, partitions = "0"), groupId = "TEST_GROUP")
    protected void consume(ConsumerRecord<String, String> msg) throws IOException {
        UserChangeEmail userChangeEmail = objectMapper.readValue(msg.value(), UserChangeEmail.class);
        LOG.info("Got msg from kafka = [{}]", userChangeEmail);
        if (userService.updateEmail(userChangeEmail)) {
            String msgSuccess = "Email of user with id = [{}] was updated to value = [{}]".formatted(userChangeEmail.getId(), userChangeEmail.getNewEmail());
            kafkaTemplate.send(USER_UPDATE_EMAIL, 1, null, objectMapper.writeValueAsBytes(msgSuccess)).whenComplete(
                (res, ex) -> {
                    if (ex == null) {
                        LOG.info("Msg was sent to topic = [{}] successful, offset = [{}]", USER_UPDATE_EMAIL, res.getRecordMetadata().offset());
                    } else {
                        LOG.info("Msg did not send to topic = [{}]", USER_UPDATE_EMAIL);
                    }
                }
            );
        } else {
            // to do something
        }
    }
}
