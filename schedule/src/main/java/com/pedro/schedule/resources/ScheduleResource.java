package com.pedro.schedule.resources;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;
import com.pedro.schedule.services.IScheduleService;
import com.pedro.schedule.services.impl.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleResource {

    ModelMapper modelMapper;

   IScheduleService service;

   @Autowired
    public ScheduleResource(ModelMapper modelMapper, IScheduleService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/y-xml"})
    public ResponseEntity<ScheduleDTO> create(@RequestBody ScheduleDTO dto) {
        Schedule schedule = service.create(dto);
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

        return ResponseEntity.ok().body(scheduleDTO);
    }
}
