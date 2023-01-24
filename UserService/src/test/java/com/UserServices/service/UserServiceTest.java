package com.UserServices.service;
import com.UserServices.entity.Hotel;
import com.UserServices.entity.Rating;
import com.UserServices.entity.User;
import com.UserServices.external.RatingService;
import com.UserServices.repo.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Mock
    private RatingService ratingService;

    @Test
    void getAllUser() {
        User user = new User();
        user.setUserId("12");
        user.setName("abc");
        user.setEmail("vsvv@gmail.com");
        user.setAbout("hello");

        User user1 = new User();
        user1.setUserId("13");
        user1.setName("anc");
        user1.setEmail("jsjj@gmail.com");
        user1.setAbout("hii");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        when(userRepo.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUser();
        //Positive Test case
        assertEquals(2, users.size());
        assertNotNull(users);
        //Negative Test Case
      // assertEquals(3, userList.size());
    }
    @Test
    void saveUser() {
        User user = new User();
        user.setUserId("12");
        user.setName("abc");
        user.setEmail("vsvv@gmail.com");
        user.setAbout("hello");

        when(userRepo.save(user)).thenReturn(user);

        User user1 = userService.addUser(user);

        assertNotNull(user1);
        assertThat(user.getName()).isEqualTo(user1.getName());
    }
    @Test
    void getUser() {
        User user = new User();
        user.setUserId("12");
        user.setName("abc");
        user.setEmail("vsvv@gmail.com");
        user.setAbout("hello");
        Hotel  hotel = new Hotel();
        hotel.setId("2");
        hotel.setLocation("location 1");
        hotel.setName("XYX");
        hotel.setAbout("nice");
        Rating rating = new Rating();
        rating.setUserId(user.getUserId());
        rating.setRatingId("5");
        rating.setRating(8);
        rating.setHotelId(hotel.getId());
       // rating.setHotel(hotel);

        User user1 = userRepo.findById("12").orElse(null);
        List<Rating> ratingList =  ratingService.getList(user.getUserId());
        List<Rating> ratings = ratingList.stream().map(rating1 -> {
            rating1.setHotel(hotel);
            return rating1;
        }).collect(Collectors.toList());
        user1.setRatingList(ratings);
        assertEquals(ratingList.size(), ratings.size());
    }


}