package com.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{

    private Long id;
    private String name;
    private String email;

//    public UserDto(Long id, String email,String name){
//        this.id = id;
//        this.email=email;
//        this.name = name;
//    }
}
