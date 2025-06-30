package com.rating.repo;

import com.rating.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating, String> {


    //custome method to making

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
