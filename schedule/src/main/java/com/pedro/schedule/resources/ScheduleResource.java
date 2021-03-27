package com.pedro.schedule.resources;

import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;
import com.pedro.schedule.services.IScheduleService;
import com.pedro.schedule.services.impl.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> index() {
       List<Schedule> schedule = service.findAll();
        List<ScheduleDTO> dto = new ArrayList<>();
        schedule.stream().map(x -> dto.add(modelMapper.map(x, ScheduleDTO.class)));
        return ResponseEntity.ok().body(dto);

    }
}
