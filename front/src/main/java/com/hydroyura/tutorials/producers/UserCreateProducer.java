package com.hydroyura.tutorials.producers;

import com.hydroyura.tutorials.models.MsgContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.hydroyura.tutorials.consts.Topics.USER_CREATE;


@Service
public class UserCreateProducer {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, MsgContainer> kafkaTemplate;

    public void sendMsg(String user) {
        MsgContainer<String> container = new MsgContainer<>();
        container.setContent(user);
        container.setClazz(String.class);

        kafkaTemplate.send(USER_CREATE, "CREATE_NEW", container)
                .whenComplete((res, ex) -> {
                    if (ex == null) {
                        LOG.info("User was sent successful, user = [{}], offset = [{}]", user.toString(), res.getRecordMetadata().offset());
                    } else {
                        LOG.info("User did not send: user = [{}]", user.toString());
                    }
                });
    }

}
