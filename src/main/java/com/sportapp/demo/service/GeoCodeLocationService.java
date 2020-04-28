package com.sportapp.demo.service;

import com.sportapp.demo.dto.GpsDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodeLocationService {

    @Autowired
    private RestTemplate restTemplate;

    public GpsDto getLocation(String country, String city, String street, String streetNumber) throws JSONException {

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

    public GpsDto getLocation(String country, String city) throws JSONException {

        String textAdres = city + "+" + country;
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


}
