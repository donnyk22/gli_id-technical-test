package com.gli.id.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserDto extends BaseDto{
    private String name;
    private String address;
    private String email;
    private String phone;
}
