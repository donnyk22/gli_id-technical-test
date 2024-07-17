package com.gli.id.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Setter
@Getter
@Accessors(chain = true)
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    private Boolean deleted;
}
