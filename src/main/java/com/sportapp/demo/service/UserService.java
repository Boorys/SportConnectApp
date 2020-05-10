package com.sportapp.demo.service;

import com.sportapp.demo.dto.OpinionGetDto;
import com.sportapp.demo.dto.OpinionPostDto;
import com.sportapp.demo.entity.OpinionEntity;
import com.sportapp.demo.entity.UserEntity;
import com.sportapp.demo.exception.UserExistException;
import com.sportapp.demo.mapper.OpinionMapper;
import com.sportapp.demo.repository.OpinionRepository;
import com.sportapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private OpinionMapper opinionMapper;
    private OpinionRepository opinionRepository;

    @Autowired
    public UserService(UserRepository userRepository, OpinionMapper opinionMapper, OpinionRepository opinionRepository) {
        this.userRepository = userRepository;
        this.opinionMapper = opinionMapper;
        this.opinionRepository = opinionRepository;
    }

    public void addNewOpinion(OpinionPostDto userPostDto) {

        UserEntity userEntity = Optional.ofNullable(userRepository.findByUserId(userPostDto.getUserId())).orElseThrow(UserExistException::new);

        OpinionEntity opinionEntity = new OpinionEntity.Builder()
                .generalComment(userPostDto.getGeneralComment())
                .skill(userPostDto.getSkill())
                .punctuality(userPostDto.getPunctuality())
                .user(userEntity)
                .build();

        opinionRepository.save(opinionEntity);
    }

    public List<OpinionGetDto> getOpinionByUserId(String userId)  {

       UserEntity userEntity = Optional.ofNullable(userRepository.findByUserId(userId)).orElseThrow(UserExistException::new);

        List<OpinionGetDto> opinionGetDtoList = userEntity.getOpinions().stream().map(
                x -> opinionMapper.opinionEntityToOpinionGetDto(x)
        ).collect(Collectors.toList());

        return opinionGetDtoList;
    }


}
