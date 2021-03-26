package com.pedro.schedule.services.impl;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;
import com.pedro.schedule.repositories.ScheduleRepository;
import com.pedro.schedule.services.IScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {
   ModelMapper modelMapper;
   ScheduleRepository repo;

   @Autowired
    public ScheduleService(ModelMapper modelMapper, ScheduleRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public Schedule create(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        Schedule createdSchedule = repo.save(schedule);
        return createdSchedule;
    }
}
