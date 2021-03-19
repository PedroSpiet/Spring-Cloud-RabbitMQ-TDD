package com.spiet.people.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peoples")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "tellphone", nullable = false, length = 13)
    private Integer tellphone;

    @Column(name = "email", nullable = false, length = 200)
    private String email;


}
