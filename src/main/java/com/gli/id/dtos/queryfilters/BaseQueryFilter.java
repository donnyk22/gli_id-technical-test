package com.gli.id.dtos.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BaseQueryFilter {
    private Integer id;
    private String search_text;
}
