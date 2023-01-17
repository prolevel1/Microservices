package com.RatingServices.controller;

import com.RatingServices.entity.Rating;
import com.RatingServices.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private IRatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        Rating rating1 = this.ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Rating>> getRating() {
        return ResponseEntity.ok(ratingService.getRatings());
    }
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    @GetMapping("/Hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
