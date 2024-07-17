package com.gli.id.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "orders")
@SQLRestriction("deleted = false")
public class Order extends BaseModel{
    private Integer user_id;
    private Integer total_price;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> order_list;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private User user_detail;
}