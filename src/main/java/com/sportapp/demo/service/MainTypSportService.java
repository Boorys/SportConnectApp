package com.sportapp.demo.service;

import com.sportapp.demo.entity.MainTypSportEntity;
import com.sportapp.demo.exception.MainTypSportExistException;
import com.sportapp.demo.mapper.MainTypSportMapper;
import com.sportapp.demo.repository.MainTypSportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainTypSportService {

    private MainTypSportRepository mainTypSportRepository;
    private MainTypSportMapper mainTypSportMapper;

    @Autowired
    public MainTypSportService(MainTypSportRepository mainTypSportRepository, MainTypSportMapper mainTypSportMapper) {
        this.mainTypSportRepository = mainTypSportRepository;
        this.mainTypSportMapper = mainTypSportMapper;
    }

    public List getAllMainTypSportService() {

       List<MainTypSportEntity> mainTypSportEntityList = mainTypSportRepository.findAll();

       if(mainTypSportEntityList == null)
       {
           throw new MainTypSportExistException();
       }
        return mainTypSportEntityList.stream().map(x -> mainTypSportMapper.mainTypSportEntityToMainTypSportGet(x)).collect(Collectors.toList());
    }


}
