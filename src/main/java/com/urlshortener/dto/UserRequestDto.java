package com.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String name;
    private String email;
    private String secret;
    private List<UrlDto> url;

}
