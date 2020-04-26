package com.sportapp.demo.service;

import com.sportapp.demo.exception.MainTypSportExistException;
import com.sportapp.demo.mapper.MainTypSportMapper;
import com.sportapp.demo.repository.MainTypSportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class MainTypSportTest {

    @Mock
    private MainTypSportRepository mainTypSportRepository;
    @Mock
    private MainTypSportMapper mainTypSportMapper;

    @Spy
    @InjectMocks
    private MainTypSportService mainTypSportService;

    @Test
    public void getAllMainTypSportServiceShouldThrowMainTypSportException() {

        //given
        given(mainTypSportRepository.findAll()).willReturn(null);
        //when
        //then
        assertThrows(MainTypSportExistException.class, () -> mainTypSportService.getAllMainTypSportService());

    }


}
