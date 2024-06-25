package com.hydroyura.tutorials.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;


public abstract class ByteArrProducer implements BaseProducer<byte[]> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final NewTopic topic;
    private String TOPIC_NAME;

    @Autowired
    private KafkaTemplate<String, byte[]> template;

    protected ByteArrProducer(NewTopic topic) {
        this.topic = topic;
        TOPIC_NAME = topic.name();
    }

    @Override
    public void send(byte[] msg) {
        template.send(TOPIC_NAME, 0, null, msg).whenComplete(
                (res, ex) -> {
                    if (ex == null) {
                        LOG.info("Msg was sent to topic = [{}] successful, offset = [{}]", TOPIC_NAME, res.getRecordMetadata().offset());
                    } else {
                        LOG.info("Msg did not send to topic = [{}]", TOPIC_NAME);
                    }
                }
        );
    }


}

