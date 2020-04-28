package com.sportapp.demo.controller;

import com.sportapp.demo.dto.SelectedLocationPostDto;
import com.sportapp.demo.dto.LocationPostDto;
import com.sportapp.demo.dto.SportFromLocalAreaDto;
import com.sportapp.demo.service.LocationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
public class TakePartSportPlaceController {

    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add/location", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Add location")
    public void addSportWishForUser(@RequestBody LocationPostDto locationPostDto) throws ParseException {

        locationService.saveLocation(locationPostDto);
    }

    @GetMapping(path = "/get/all/sport/from/area/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<SelectedLocationPostDto> getSportsLocationFromArea(@RequestBody SportFromLocalAreaDto sportFromLocalAreaDto) throws JSONException {

        return locationService.getSportsLocationFromSelectedDistance(sportFromLocalAreaDto);
    }

}
