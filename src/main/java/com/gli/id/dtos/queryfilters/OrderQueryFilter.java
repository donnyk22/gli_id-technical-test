package com.gli.id.dtos.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderQueryFilter extends BaseQueryFilter{
    private Integer user_id;
}
