package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService rs;

    @PostMapping
    private ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = rs.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping
    private ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.ok(rs.getAllRating());
    }

    @GetMapping("/users/{userId}")
    private ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(rs.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    private ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(rs.getRatingByHotelId(hotelId));
    }

}
