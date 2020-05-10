package com.sportapp.demo.mapper;


import com.sportapp.demo.dto.OpinionGetDto;
import com.sportapp.demo.entity.OpinionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpinionMapper {

    OpinionGetDto opinionEntityToOpinionGetDto(OpinionEntity opinionEntity);

}
