package com.HotelServices.controller;

import com.HotelServices.entity.Hotel;
import com.HotelServices.service.HotelService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HotelControllerTest {
    @MockBean
    private HotelService hotelService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("hotel 1");
        hotel.setAbout("good");
        hotel.setLocation("bangalore");

        Mockito.when(hotelService.create(ArgumentMatchers.any())).thenReturn(hotel);
       // mockMvc.perform(post("/hotel/"))
        String json = objectMapper.writeValueAsString(hotel);
        mockMvc.perform(post("/hotel/").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())


               .andExpect(jsonPath("$.id", Matchers.equalTo("1")));

    }
    @Test
    void getHotelById() throws Exception{
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("hotel 1");
        hotel.setAbout("good");
        hotel.setLocation("bangalore");
        Mockito.when(hotelService.getIdHotel(hotel.getId())).thenReturn(hotel);
        mockMvc.perform(get("/hotel/api/{id}", hotel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo("1")));

    }


}