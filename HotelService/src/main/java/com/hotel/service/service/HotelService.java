package com.hotel.service.service;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel createHotel(Hotel hotel);

    //getAll
    List<Hotel> getAllHotels();

    //getone
    Hotel getHotelById(String id);

}
