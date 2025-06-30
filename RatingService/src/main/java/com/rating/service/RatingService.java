package com.rating.service;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getAllRating();

    //get rating by userid
    List<Rating> getRatingByUserId(String ratingId);

    //get rating  by hotelId
    List<Rating> getRatingByHotelId(String hotelId);




}
