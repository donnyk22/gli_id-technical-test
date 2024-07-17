package com.gli.id.tools;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Converter {
    public static ZonedDateTime toZonedDateTime(Date date){
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
