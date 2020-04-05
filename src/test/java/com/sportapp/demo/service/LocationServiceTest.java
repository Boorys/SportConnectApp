package com.sportapp.demo.service;

import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.entity.UserEntity;
import com.sportapp.demo.exception.MainTypSportExistException;
import com.sportapp.demo.exception.UserExistException;
import com.sportapp.demo.mapper.LocationMapper;
import com.sportapp.demo.mapper.TimeTableMapper;
import com.sportapp.demo.repository.LocationRepository;
import com.sportapp.demo.repository.MainTypSportRepository;
import com.sportapp.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private LocationPostDto locationPostDto;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MainTypSportRepository mainTypSportRepository;
    @Mock
    private LocationMapper locationMapper;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private TimeTableMapper timeTableMapper;
    @Spy
    @InjectMocks
    LocationService locationService;

    @BeforeEach
    public void init()
    {
        locationPostDto = new LocationPostDto();
    }


    @Test
    public void addLocationShouldThrowUserExistException() {

        //given
        //when
        //then
        assertThrows(UserExistException.class, () -> locationService.saveLocation(locationPostDto));
    }

    @Test
    public void addLocationShouldThrowMainTypExistException() {

        //given
        locationPostDto.setUserId("id");

        given(userRepository.findByUserId(anyString())).willReturn(new UserEntity());
        //when
        //then
        assertThrows(MainTypSportExistException.class, () -> locationService.saveLocation(locationPostDto));
    }


}
