package com.HotelServices.service;

import com.HotelServices.entity.Hotel;
import com.HotelServices.repo.IHotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelService implements IHotelService{
    @Autowired
    private IHotelRepo hotelRepo;
    @Override
    public Hotel create(Hotel hotel) {
        return this.hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepo.findAll();
    }

    @Override
    public Hotel getIdHotel(String id) {
        Hotel hotel =  hotelRepo.findById(id).orElse(null);
        return  hotel;
    }
}
