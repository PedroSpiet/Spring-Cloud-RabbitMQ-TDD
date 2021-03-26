package com.pedro.schedule.services;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;

public interface IScheduleService {
    Schedule create(ScheduleDTO scheduleDTO);
}
