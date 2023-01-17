package com.UserServices.service;

import com.UserServices.entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    List< User > getAllUser();

    User getUser(String userId);
}
