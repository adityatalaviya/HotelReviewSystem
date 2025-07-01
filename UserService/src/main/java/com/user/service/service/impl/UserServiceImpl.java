package com.user.service.service.impl;

import com.user.service.entity.Hotel.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repo.UserRepo;
import com.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User createUser(User user) {
        //generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get single user with database with help of user repo
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist" + userId));
        //get rating for users from RatingService

        Rating[] list = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
        logger.info("Rating list is : " + list);

        List<Rating> ratings = Arrays.stream(list).collect(Collectors.toList());
        List<Rating> ratingList= ratings.stream().map(rating -> {
            //ResponseEntity<Hotel> hotelRating = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("status code : ",hotelRating.getStatusCode());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);


        return user;
    }

    @Override
    public User updateUser(String userId, User user) {
        User existUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setAbout(user.getAbout());
        return userRepo.save(existUser);
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}
