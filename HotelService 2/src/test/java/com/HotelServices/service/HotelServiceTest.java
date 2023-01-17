package com.HotelServices.service;

import com.HotelServices.entity.Hotel;
import com.HotelServices.repo.IHotelRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {
    @Mock
    IHotelRepo hotelRepo;

    @InjectMocks
    HotelService hotelService;

    @Test
    void saveHotel() {
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("hotel 1");
        hotel.setAbout("good");
        hotel.setLocation("location 1");
        when(hotelRepo.save(hotel)).thenReturn(hotel);

        Hotel hotel1 = hotelService.create(hotel);
        assertNotNull(hotel1);
        //Negative Scenario
        assertEquals("abc", hotel1.getName());
    }
    @Test
    void getHotelById() {
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("hotel 1");
        hotel.setAbout("good");
        hotel.setLocation("location 1");
        String id = "2";
        when(hotelRepo.findById(id)).thenReturn(Optional.of(hotel));
        Hotel hotel1 = hotelService.getIdHotel(id);
        assertNotNull(hotel1);
        assertEquals(id, hotel1.getId());

    }



}