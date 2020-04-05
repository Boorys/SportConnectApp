package com.sportapp.demo.service;

import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.entity.LocationEntity;
import com.sportapp.demo.entity.MainTypSportEntity;
import com.sportapp.demo.entity.TimeTableEntity;
import com.sportapp.demo.entity.UserEntity;
import com.sportapp.demo.exception.MainTypSportExistException;
import com.sportapp.demo.exception.UserExistException;
import com.sportapp.demo.mapper.LocationMapper;
import com.sportapp.demo.mapper.TimeTableMapper;
import com.sportapp.demo.repository.LocationRepository;
import com.sportapp.demo.repository.MainTypSportRepository;
import com.sportapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {


    private UserRepository userRepository;

    private MainTypSportRepository mainTypSportRepository;

    private LocationMapper locationMapper;

    private LocationRepository locationRepository;

    private TimeTableMapper timeTableMapper;


    @Autowired
    public LocationService(UserRepository userRepository, MainTypSportRepository mainTypSportRepository, LocationMapper locationMapper, LocationRepository locationRepository, TimeTableMapper timeTableMapper) {
        this.userRepository = userRepository;
        this.mainTypSportRepository = mainTypSportRepository;
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
        this.timeTableMapper = timeTableMapper;
    }

    @Transactional
    public void saveLocation(LocationPostDto locationPostDto) {

        UserEntity userEntity = userRepository.findByUserId(locationPostDto.getUserId());

        if (userEntity == null) {
            throw new UserExistException();
        }


        MainTypSportEntity mainTypSportEntity = mainTypSportRepository.findByMainTypSportEntityId(locationPostDto.getMainTypSportEntityId());
        if (mainTypSportEntity == null) {
            throw new MainTypSportExistException();
        }

        LocationEntity locationEntity = locationMapper.locationPostDtoTolocationEntity(locationPostDto);
        TimeTableEntity timeTableEntity = timeTableMapper.locationPostDtoToTimeTableEntity(locationPostDto);

        saveMainTypSportToUser(mainTypSportEntity, userEntity);
        saveLocationToMainTypSport(locationEntity, timeTableEntity, mainTypSportEntity);
    }

    private void saveMainTypSportToUser(MainTypSportEntity mainTypSportEntity, UserEntity userEntity) {

        List<MainTypSportEntity> mainTypSportEntityList = userEntity.getMainTypSports();
        if (mainTypSportEntityList == null) {
            mainTypSportEntityList = new ArrayList<>();
        }
        mainTypSportEntityList.add(mainTypSportEntity);
        userEntity.setMainTypSports(mainTypSportEntityList);
        userRepository.save(userEntity);
    }

    private void saveLocationToMainTypSport(LocationEntity locationEntity, TimeTableEntity timeTableEntity, MainTypSportEntity mainTypSportEntity) {

        List<MainTypSportEntity> list = locationEntity.getMainTypSports();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(mainTypSportEntity);

        locationEntity.setMainTypSports(list);
        List<TimeTableEntity> timeTableEntities = locationEntity.getTimeTableEntities();
        if (timeTableEntities == null) {
            timeTableEntities = new ArrayList<>();
        }
        timeTableEntities.add(timeTableEntity);
        locationEntity.setTimeTableEntities(timeTableEntities);
        locationRepository.save(locationEntity);

    }

}
