package com.gli.id.dtos.mappers;

import com.gli.id.dtos.UserDto;
import com.gli.id.models.User;

public class UserMapper {
    public static UserDto toDto(User model){
        UserDto userDto = new UserDto()
                .setName(model.getName())
                .setAddress(model.getAddress())
                .setPhone(model.getPhone())
                .setEmail(model.getEmail());
        userDto.setId(model.getId());
        return userDto;
    }
}
