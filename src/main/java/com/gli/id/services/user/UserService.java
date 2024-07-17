package com.gli.id.services.user;

import com.gli.id.dtos.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
}
