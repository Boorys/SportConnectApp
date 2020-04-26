package com.sportapp.demo.mapper;

import com.sportapp.demo.dto.MainTypSportGet;
import com.sportapp.demo.entity.MainTypSportEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainTypSportMapper {

MainTypSportGet mainTypSportEntityToMainTypSportGet(MainTypSportEntity mainTypSportEntity);

}
