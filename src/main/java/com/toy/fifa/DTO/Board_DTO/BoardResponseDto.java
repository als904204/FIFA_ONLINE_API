package com.toy.fifa.DTO.Board_DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto<T> {

    /*
    * return 1 : 성공
    * */
    int status;
    T data;
}
