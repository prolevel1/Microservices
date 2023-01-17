package com.HotelServices.service;

import com.HotelServices.entity.Hotel;

import java.util.List;

public interface IHotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getIdHotel(String id);
}
