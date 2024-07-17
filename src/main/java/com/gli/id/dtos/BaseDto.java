package com.gli.id.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class BaseDto {
    private Integer id;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
}
