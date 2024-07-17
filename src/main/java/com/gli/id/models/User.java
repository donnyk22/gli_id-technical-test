package com.gli.id.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "users")
@SQLRestriction("deleted = false")
public class User extends BaseModel{
    private String name;
    private String address;
    private String email;
    private String phone;
}