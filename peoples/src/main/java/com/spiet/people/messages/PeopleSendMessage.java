package com.spiet.people.messages;

import com.spiet.people.DTOs.PeopleDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PeopleSendMessage {
    @Value("${peoples.rabbitmq.exchange}")
    String exchange;

    @Value("${peoples.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    public PeopleSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(PeopleDTO peopleDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, peopleDTO);
    }
}
