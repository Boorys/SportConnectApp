package com.sportapp.demo.mapper;

import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.entity.TimeTableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeTableMapper {

    TimeTableEntity locationPostDtoToTimeTableEntity(LocationPostDto locationPostDto);
}
