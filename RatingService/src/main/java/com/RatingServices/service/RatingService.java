package com.RatingServices.service;

import com.RatingServices.entity.Rating;
import com.RatingServices.repo.IRatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingService implements IRatingService{
    @Autowired
    private IRatingRepo ratingRepo;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);

    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
