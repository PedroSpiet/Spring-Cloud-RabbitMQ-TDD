package com.pedro.schedule.services;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;

import java.util.List;

public interface IScheduleService {
    Schedule create(ScheduleDTO scheduleDTO);
    List<Schedule> findAll();
}
