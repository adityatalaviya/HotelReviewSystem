package com.rating.service.Impl;

import com.rating.entity.Rating;
import com.rating.repo.RatingRepo;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo repo;


    @Override
    public Rating create(Rating rating) {
        return repo.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repo.findByHotelId(hotelId);
    }
}
