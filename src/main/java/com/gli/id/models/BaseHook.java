package com.gli.id.models;

import com.gli.id.tools.Converter;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class BaseHook {

    @PrePersist
    public void executeBeforeSave(final BaseModel model){
        model.setCreated_at(Converter.toZonedDateTime(new Date()));
        model.setUpdated_at(Converter.toZonedDateTime(new Date()));
        model.setDeleted(false);
    }
    @PreUpdate
    public void executeBeforeUpdate(final BaseModel model){
        model.setUpdated_at(Converter.toZonedDateTime(new Date()));
    }

}