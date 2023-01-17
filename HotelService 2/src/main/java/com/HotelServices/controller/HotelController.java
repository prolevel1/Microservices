package com.HotelServices.controller;

import com.HotelServices.entity.Hotel;
import com.HotelServices.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> create(@RequestBody  Hotel hotel) {
        Hotel hotel1 = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);

    }
    @GetMapping("/api/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        Hotel hotel = hotelService.getIdHotel(id);
        return ResponseEntity.ok(hotel);
    }
}
