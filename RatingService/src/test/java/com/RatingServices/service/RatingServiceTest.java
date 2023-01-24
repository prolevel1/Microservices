package com.RatingServices.service;

import com.RatingServices.entity.Rating;
import com.RatingServices.repo.IRatingRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RatingServiceTest {
    @Mock
    IRatingRepo ratingRepo;
    @InjectMocks
    RatingService ratingService;

    @Test
    void saveRating() {
        Rating rating = new Rating();
        rating.setRating(18);
        rating.setRating(5);
        rating.setFeedback("Nice ambience");
        rating.setHotelId("1");
        rating.setUserId("2");

        when(ratingRepo.save(rating)).thenReturn(rating);

        Rating rating1 = ratingService.createRating(rating);

        assertNotNull(rating1);
        assertThat(rating.getFeedback()).isEqualTo(rating1.getFeedback());
    }
    @Test
    void getAllRatingByHotelId() {
        Rating rating = new Rating();
        rating.setRating(18);
        rating.setRating(5);
        rating.setFeedback("Nice ambience");
        rating.setHotelId("7");
        rating.setUserId("2");

        Rating rating1 = new Rating();
        rating1.setRating(15);
        rating1.setRating(8);
        rating1.setFeedback("Nice ");
        rating1.setHotelId("6");
        rating1.setUserId("4");
        String hotel_id = "1";
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        ratingList.add(rating1);
        when(ratingRepo.findByHotelId(hotel_id)).thenReturn(ratingList);

        List<Rating> ratings = ratingService.getRatingByHotelId(hotel_id);

        assertNotNull(ratings);

      //  assertEquals(ratings.size(), ratingList.size());

    }
    @Test
    void getRatingByUserId() {
        Rating rating = new Rating();
        rating.setRating(18);
        rating.setRating(5);
        rating.setFeedback("Nice ambience");
        rating.setHotelId("7");
        rating.setUserId("2");
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);

        when(ratingRepo.findByUserId(rating.getUserId())).thenReturn(ratingList);

        List<Rating> ratings = ratingService.getRatingByUserId(rating.getUserId());
        //Negative test case scenario
        assertEquals(1, ratings.size());
        //Positive Test case scenario
        assertNotNull(ratings);
    }
    @Test
    void getAllRating() {
        Rating rating = new Rating();
        rating.setRating(18);
        rating.setRating(5);
        rating.setFeedback("Nice ambience");
        rating.setHotelId("7");
        rating.setUserId("2");

        Rating rating1 = new Rating();
        rating1.setRating(1);
        rating1.setRating(9);
        rating1.setFeedback("Nice ambience");
        rating1.setHotelId("2");
        rating1.setUserId("3");
        List<Rating> ratings = new ArrayList<>();

        ratings.add(rating);
        ratings.add(rating1);
        when(ratingRepo.findAll()).thenReturn(ratings);

        List<Rating> ratingList = ratingService.getRatings();
        assertEquals(ratings.size(), ratingList.size());


    }

}