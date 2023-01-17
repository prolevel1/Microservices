package com.UserServices.external;

import com.UserServices.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @GetMapping("/rating/getUser/{userId}")
     List<Rating> getList(@PathVariable("userId") String userId);
//    @GetMapping("/rating/get")
//    List<Rating> getRate();
}

