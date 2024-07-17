package com.gli.id.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "products")
@SQLRestriction("deleted = false")
public class Product extends BaseModel{
    private String product_name;
    private Date expired_date;
    private Integer price;
}
