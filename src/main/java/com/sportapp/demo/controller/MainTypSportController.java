package com.sportapp.demo.controller;


import com.sportapp.demo.service.MainTypSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class MainTypSportController {

    MainTypSportService mainTypSportService;

    @Autowired
    public MainTypSportController(MainTypSportService mainTypSportService) {
        this.mainTypSportService = mainTypSportService;
    }

    @GetMapping(path = "/get/location/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List getMainTypSport()
    {
      return mainTypSportService.getAllMainTypSportService();
    }


}
