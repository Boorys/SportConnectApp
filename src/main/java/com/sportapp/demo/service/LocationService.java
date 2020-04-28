package com.sportapp.demo.service;

import com.sportapp.demo.dto.GpsDto;
import com.sportapp.demo.dto.SelectedLocationPostDto;
import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.dto.SportFromLocalAreaDto;
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
import com.sportapp.demo.repository.TimeTableRepository;
import com.sportapp.demo.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class LocationService {

    private UserRepository userRepository;

    private MainTypSportRepository mainTypSportRepository;

    private LocationMapper locationMapper;

    private LocationRepository locationRepository;

    private TimeTableMapper timeTableMapper;

    private GeoCodeLocationService geoCodeLocationService;

    private TimeTableRepository timeTableRepository;


    @Autowired
    public LocationService(UserRepository userRepository, MainTypSportRepository mainTypSportRepository, LocationMapper locationMapper,
                           LocationRepository locationRepository, TimeTableMapper timeTableMapper, GeoCodeLocationService geoCodeLocationService, TimeTableRepository timeTableRepository) {
        this.userRepository = userRepository;
        this.mainTypSportRepository = mainTypSportRepository;
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
        this.timeTableMapper = timeTableMapper;
        this.geoCodeLocationService = geoCodeLocationService;
        this.timeTableRepository = timeTableRepository;
    }


    @Transactional
    public void saveLocation(LocationPostDto locationPostDto) throws ParseException {

        UserEntity userEntity = userRepository.findByUserId(locationPostDto.getUserId());
        GpsDto gpsDto = new GpsDto();
        if (userEntity == null) {
            throw new UserExistException();
        }
        try {
            gpsDto = geoCodeLocationService.getLocation(locationPostDto.getCountry(), locationPostDto.getCity(), locationPostDto.getStreet(), locationPostDto.getStreetNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainTypSportEntity mainTypSportEntity = mainTypSportRepository.findByMainTypSportEntityId(locationPostDto.getMainTypSportEntityId());

        if (mainTypSportEntity == null) {
            throw new MainTypSportExistException();
        }

        LocationEntity locationEntity = locationMapper.locationPostDtoTolocationEntity(locationPostDto);
        locationEntity.setLang(gpsDto.getLang());
        locationEntity.setLat(gpsDto.getLat());

        TimeTableEntity timeTableEntity = timeTableMapper.locationPostDtoToTimeTableEntity(locationPostDto);

        timeTableEntity.setDate(getDate(locationPostDto));

        saveMainTypSportToUser(mainTypSportEntity, userEntity);

        saveLocationToMainTypSport(locationEntity, timeTableEntity, mainTypSportEntity);
    }

    private Date getDate(LocationPostDto locationPostDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Calendar calendar = new GregorianCalendar(locationPostDto.getYear(), locationPostDto.getMonth(), locationPostDto.getDay(), locationPostDto.getHour(), locationPostDto.getMinute());

        return calendar.getTime();
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
        timeTableEntity.setLocation(locationEntity);
        timeTableRepository.save(timeTableEntity);
        locationRepository.save(locationEntity);
    }

    public List<SelectedLocationPostDto> getSportsLocationFromSelectedDistance(SportFromLocalAreaDto sportFromLocalAreaDto) throws JSONException {

        GpsDto gpsDto = geoCodeLocationService.getLocation(sportFromLocalAreaDto.getCountry(),sportFromLocalAreaDto.getCity());

        var selectedLocationPostDto = locationRepository.findLocationToSelecteddistance( Float.toString(gpsDto.getLang()),
                Float.toString(gpsDto.getLat()), Double.toString(sportFromLocalAreaDto.getDistance() * 0.0130938));
        return selectedLocationPostDto.stream().map(x -> new SelectedLocationPostDto(x[0].toString(), x[1].toString(), x[2].toString(), x[3].toString(), (Date) x[4],x[5].toString(),x[6].toString())).collect(Collectors.toList());

    }


}
