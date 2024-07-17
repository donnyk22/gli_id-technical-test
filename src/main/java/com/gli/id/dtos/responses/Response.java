package com.gli.id.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Response<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseEntity build(){
        return ResponseEntity.status(this.status).body(this);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setStatus(200);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setStatus(500);
        response.setMessage(message);
        return response;
    }
}
