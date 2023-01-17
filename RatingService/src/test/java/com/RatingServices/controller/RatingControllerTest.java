package com.RatingServices.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import com.RatingServices.entity.Rating;

import com.RatingServices.service.RatingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRatingPost() throws Exception {
        Rating rating = new Rating();
        rating.setRating(18);
       rating.setRatingId("7");
        rating.setFeedback("Nice ambience");
        rating.setHotelId("7");
        rating.setUserId("2");
        Mockito.when(ratingService.createRating(ArgumentMatchers.any())).thenReturn(rating);
        String json = objectMapper.writeValueAsString(rating);
        mockMvc.perform(post("/rating/add").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.ratingId", Matchers.equalTo("7")));


    }
    @Test
    void  getAll() throws Exception {
        Rating rating = new Rating();
        List<Rating> ratings = new ArrayList<>();
        rating.setRating(18);
        rating.setRating(5);
        rating.setFeedback("nice");
        rating.setHotelId("7");
        rating.setUserId("2");
        ratings.add(rating);

        Mockito.when(ratingService.getRatings()).thenReturn(ratings);
        mockMvc.perform(get("/rating/get")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].feedback", Matchers.equalTo("nice")));
    }
// /rating/getUser/{userId}
    @Test
    void getAllRatingUserId() throws Exception {
        String userId = "2";

        List<Rating> ratings = new ArrayList<>();
        Rating rating = new Rating();
        rating.setRatingId("18");
        rating.setRating(5);
        rating.setFeedback("nice");
        rating.setHotelId("7");
        rating.setUserId("2");
        ratings.add(rating);
        Mockito.when(ratingService.getRatingByUserId(userId)).thenReturn(ratings);
        mockMvc.perform(get("/rating/getUser/2", userId))
                 .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating", Matchers.equalTo(5)));

    }
    @Test
    void getAllRatingHotelId() throws Exception{
        List<Rating> ratings = new ArrayList<>();
        Rating rating = new Rating();
        rating.setRatingId("18");
        rating.setRating(5);
        rating.setFeedback("nice");
        rating.setHotelId("7");
        rating.setUserId("2");
        ratings.add(rating);

        Mockito.when(ratingService.getRatingByHotelId(rating.getHotelId())).thenReturn(ratings);
        mockMvc.perform(get("/rating/Hotel/{hotelId}", rating.getHotelId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", Matchers.equalTo("2")));


    }

}