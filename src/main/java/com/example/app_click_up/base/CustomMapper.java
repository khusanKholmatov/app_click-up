package com.example.app_click_up.base;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomMapper<T, R> {

    // T - ENTITY  R- DTO
    T dtoToObject(R r);

    R objectToDto(T t);

    T dtoToObject(R r, T t);

}
