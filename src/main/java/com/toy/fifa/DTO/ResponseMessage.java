package com.toy.fifa.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ResponseMessage<T> {

    private int status;
    private String message;
    private T data;

    public ResponseMessage(int status, String message, T data) {
        this.status = status;
        this.message = null;
        this.data = null;
    }


}
