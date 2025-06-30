package com.hotel.service.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;


}
