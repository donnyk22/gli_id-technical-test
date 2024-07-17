package com.gli.id.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "order_details")
@SQLRestriction("deleted = false")
public class OrderDetail extends BaseModel{
    private Integer order_id;
    private Integer product_id;
    private Integer qty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false, nullable = false)
    private Product product_detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    private Order order;
}