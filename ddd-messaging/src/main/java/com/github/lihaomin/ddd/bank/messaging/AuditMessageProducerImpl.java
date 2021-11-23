package com.github.lihaomin.ddd.bank.messaging;

import com.github.lihaomin.ddd.domain.types.AuditMessage;
import com.github.lihaomin.ddd.messaging.AuditMessageProducer;
import org.springframework.stereotype.Component;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
public class AuditMessageProducerImpl implements AuditMessageProducer {
    private static final String TOPIC_AUDIT_LOG = "TOPIC_AUDIT_LOG";

    @Override
    public Boolean send(AuditMessage message) {
        String messageBody = message.serialize();
        // TODO send kafak
        return true;
    }
}
