package com.example.modular_booking_system.auditing.listener;

import com.example.modular_booking_system.auditing.model.AuditLog;
import com.example.modular_booking_system.auditing.repository.AuditLogRepository;
import com.example.modular_booking_system.core.config.RabbitMQConfig;
import com.example.modular_booking_system.core.events.PaymentAuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentAuditEventListener {

    private final AuditLogRepository repository;

    // Method that listens for messages from the PAYMENT_AUDIT_QUEUE.
    // When a PaymentAuditEvent is received, it creates and saves an AuditLog entity.
    @RabbitListener(queues = RabbitMQConfig.PAYMENT_AUDIT_QUEUE)
    public void handleAuditEvent(PaymentAuditEvent event) {
        repository.save(new AuditLog(
                null,
                event.getAction(),
                event.getPaymentId(),
                event.getUserId(),
                event.getUsername(),
                event.getAmount(),
                event.getTimestamp()
        ));
    }
}
