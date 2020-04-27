package com.sportapp.demo.service;

import com.sportapp.demo.dto.GpsDto;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class LocationService {


    private UserRepository userRepository;

    private MainTypSportRepository mainTypSportRepository;

    private LocationMapper locationMapper;

    private LocationRepository locationRepository;

    private TimeTableMapper timeTableMapper;

    private RestTemplate restTemplate;

    @Autowired
    public LocationService(UserRepository userRepository, MainTypSportRepository mainTypSportRepository, LocationMapper locationMapper, LocationRepository locationRepository, TimeTableMapper timeTableMapper, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.mainTypSportRepository = mainTypSportRepository;
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
        this.timeTableMapper = timeTableMapper;
        this.restTemplate = restTemplate;
    }


    @Transactional
    public void saveLocation(LocationPostDto locationPostDto) {

        UserEntity userEntity = userRepository.findByUserId(locationPostDto.getUserId());
        GpsDto gpsDto = new GpsDto();
        if (userEntity == null) {
            throw new UserExistException();
        }
        try {
            gpsDto = getLocation(locationPostDto.getCountry(), locationPostDto.getCity(), locationPostDto.getStreet(), locationPostDto.getStreetNumber());
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

        saveMainTypSportToUser(mainTypSportEntity, userEntity);
        saveLocationToMainTypSport(locationEntity, timeTableEntity, mainTypSportEntity);
    }

    private GpsDto getLocation(String country, String city, String street, String streetNumber) throws JSONException {

        String readyStreet = street.replace(" ", "+");

        String textAdres = streetNumber + "+" + readyStreet + "+" + city + "+" + country;
        String url = "https://geocode.xyz/?scantext=" + textAdres + "&json=1";
        GpsDto gpsDto = new GpsDto();

        String jsonString = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray objectJSONArray = jsonObject.getJSONArray("match");
        jsonObject = objectJSONArray.getJSONObject(0);

        gpsDto.setLang(Float.parseFloat(jsonObject.get("longt").toString()));
        gpsDto.setLat(Float.parseFloat(jsonObject.get("latt").toString()));

        return gpsDto;
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
