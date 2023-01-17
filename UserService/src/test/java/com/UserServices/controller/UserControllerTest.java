package com.UserServices.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.UserServices.entity.Hotel;
import com.UserServices.entity.Rating;
import com.UserServices.entity.User;
import com.UserServices.service.UserService;
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

import java.util.ArrayList;
import java.util.List;


@WebMvcTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addUser () throws Exception {
        User user = new User();
        user.setUserId("12");
        user.setName("abc");
        user.setEmail("bns@gmail.com");
        user.setAbout("Hii");
        Mockito.when(userService.addUser(ArgumentMatchers.any())).thenReturn(user);
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("ab")));
    }
    @Test
    void getUser() throws Exception {
        User user = new User();
        List<Rating> ratingList = new ArrayList<>();
        user.setUserId("12");
        user.setName("abc");
        user.setEmail("bns@gmail.com");
        user.setAbout("Hii");
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("blue haeaven");
        hotel.setAbout("nice hotel");
        hotel.setLocation("Goa");

        Rating rating = new Rating();
        rating.setHotel(hotel);
        ratingList.add(rating);
        user.setRatingList(ratingList);

        Mockito.when(userService.getUser(user.getUserId())).thenReturn(user);
       // mockMvc.perform(get("/users/{userId}", user.getUserId())
        mockMvc.perform(get("/users/{userId}", user.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("abc")));

    }


}