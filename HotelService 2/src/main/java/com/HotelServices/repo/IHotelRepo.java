package com.HotelServices.repo;

import com.HotelServices.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepo extends JpaRepository<Hotel, String> {


}
