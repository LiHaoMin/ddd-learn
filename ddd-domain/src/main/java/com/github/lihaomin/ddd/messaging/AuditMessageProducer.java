package com.github.lihaomin.ddd.messaging;

import com.github.lihaomin.ddd.domain.types.AuditMessage;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public interface AuditMessageProducer {
    Boolean send(AuditMessage message);
}
