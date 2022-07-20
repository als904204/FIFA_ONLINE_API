package com.toy.fifa.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ResponseUserDto<T> {
    int status;
    T data;

    public ResponseUserDto(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
