package com.sportapp.demo.service;

import com.sportapp.demo.dto.OpinionGetDto;
import com.sportapp.demo.dto.OpinionPostDto;
import com.sportapp.demo.entity.OpinionEntity;
import com.sportapp.demo.entity.UserEntity;
import com.sportapp.demo.exception.UserExistException;
import com.sportapp.demo.mapper.OpinionMapper;
import com.sportapp.demo.repository.OpinionRepository;
import com.sportapp.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;
    @Spy
    private OpinionMapper opinionMapper;
    @Mock
    private OpinionRepository opinionRepository;
    @Spy
    @InjectMocks
    private UserService userService;


    @Test
    public void addNewOpinionShouldThrowUserExistException() {

        //given
        OpinionPostDto opinionPostDto = new OpinionPostDto();
        opinionPostDto.setUserId("id");
        //when
        when(userRepository.findByUserId(anyString())).thenReturn(null);
        //then
        assertThrows(UserExistException.class, () -> userService.addNewOpinion(opinionPostDto));
    }


    @Test
    public void getOpinionByUserIdShouldThrowUserExistException() {

        //given
        String userId = "id";
        //when
        when(userRepository.findByUserId(anyString())).thenReturn(null);
        //then
        assertThrows(UserExistException.class, () -> userService.getOpinionByUserId(userId));

    }

    @Test
    public void getOpinionByUserIdShouldReturnListOpinionGetDto() {

        //given
        UserEntity userEntity = new UserEntity();
        OpinionEntity opinionEntity = new OpinionEntity();
        userEntity.setOpinions(List.of(opinionEntity));
        //when
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
        //then
        Assert.assertEquals(userService.getOpinionByUserId(anyString()).size(), 1);

    }


}
