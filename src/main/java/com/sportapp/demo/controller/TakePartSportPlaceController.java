package com.sportapp.demo.controller;

import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TakePartSportPlaceController {

    @Autowired
    LocationService locationService;

    @PostMapping(path="/add/location",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED,reason = "Add location")
    public void addSportWishForUser(@RequestBody LocationPostDto locationPostDto)  {

        locationService.saveLocation(locationPostDto);
    }

}
