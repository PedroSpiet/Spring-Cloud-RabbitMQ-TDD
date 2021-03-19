package com.spiet.people.DTOs;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDTO extends RepresentationModel<PeopleDTO> implements Serializable {

    private Long id;

    private String name;

    private Integer tellphone;

    private String email;
}
