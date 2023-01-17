package com.RatingServices.service;

import com.RatingServices.entity.Rating;

import java.util.List;

public interface IRatingService {
    Rating createRating(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);


}
