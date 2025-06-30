package com.hotel.service.service.Impl;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repo.HotelRepo;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;


    @Override
    public Hotel createHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not found"));
    }
}
