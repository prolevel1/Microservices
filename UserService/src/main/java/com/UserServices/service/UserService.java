package com.UserServices.service;

import com.UserServices.entity.Hotel;
import com.UserServices.entity.Rating;
import com.UserServices.entity.User;
import com.UserServices.external.HotelService;
import com.UserServices.external.RatingService;
import com.UserServices.repo.IUserRepo;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    //private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public User addUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = this.userRepo.findById(userId).orElse(null);
        List<Rating> r = ratingService.getList(userId);
        List<Rating> ratingList = r.stream().map(rating -> {
            Hotel hotel = hotelService.getById(rating.getHotelId());
            rating.setHotel(hotel);
           return rating;
        }).collect(Collectors.toList());
        user.setRatingList(ratingList);
        //http://localhost:8086/rating/getuser/1233
     //   ArrayList obj = restTemplate.getForObject("http://localhost:8086/rating/getuser/1233",ArrayList.class);

        return user;
    }

}
