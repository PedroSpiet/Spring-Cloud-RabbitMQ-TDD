package com.pedro.schedule.config;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;
import com.pedro.schedule.repositories.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PeopleReceiveMessage {
    private final ScheduleRepository repo;

    @Autowired
    public PeopleReceiveMessage(ScheduleRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = {"${schedule.rabbitmq.queue}"})
    public void receive(@Payload ScheduleDTO dto) {
        Schedule convert = new ModelMapper().map(dto, Schedule.class);
        repo.save(convert);
    }
}
