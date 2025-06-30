package com.hotel.service.controller;

import com.hotel.service.entity.Hotel;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody  Hotel hotel){
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
        Hotel hotelById = hotelService.getHotelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelById);
    }

}
