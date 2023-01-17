package com.UserServices.external;

import com.UserServices.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotel/api/{hotelId}")
    Hotel getById(@PathVariable String hotelId);



}
