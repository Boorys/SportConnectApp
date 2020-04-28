package com.sportapp.demo.service;


import com.sportapp.demo.dto.GpsDto;
import com.sportapp.demo.entity.UserEntity;
import com.sportapp.demo.exception.MainTypSportExistException;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeoCodeLocationServiceTest {


    @Spy
    private RestTemplate restTemplate;

    @Spy
    @InjectMocks
    private GeoCodeLocationService geoCodeLocationService;


    @Test
    public void getLocationShouldThrowsJSONException() throws JSONException {

        //given
        String country = "Poland";
        String city = "Warszawa";
        String wrongJSON = "wrongFormat";
        String URL = "https://geocode.xyz/?scantext=Warszawa+Poland&json=1";

        //when
        when(restTemplate.getForObject(URL, String.class)).thenReturn(wrongJSON);

        //then
        assertThrows(JSONException.class, () -> geoCodeLocationService.getLocation(country, city));
    }
    @Test
    public void getLocationShouldReturnGpsDto() throws JSONException {
        //given
        String country = "Poland";
        String city = "Warszawa";
        String URL = "https://geocode.xyz/?scantext=Warszawa+Poland&json=1";

        GpsDto gpsDto;
        //when
        gpsDto =  geoCodeLocationService.getLocation(country, city);

        //then
        Assert.assertEquals(new Float(gpsDto.getLang()).getClass(),Float.class);
        Assert.assertEquals(new Float(gpsDto.getLat()).getClass(),Float.class);
    }




}
