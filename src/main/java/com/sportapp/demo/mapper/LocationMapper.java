package com.sportapp.demo.mapper;

import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationEntity locationPostDtoTolocationEntity(LocationPostDto locationPostDto);

}
