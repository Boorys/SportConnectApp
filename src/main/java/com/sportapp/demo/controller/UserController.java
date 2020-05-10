package com.sportapp.demo.controller;

import com.sportapp.demo.dto.OpinionGetDto;
import com.sportapp.demo.dto.OpinionPostDto;
import com.sportapp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(path = "/addOpinion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getOpinionByUserId(@RequestBody OpinionPostDto opinionPostDto) {
        userService.addNewOpinion(opinionPostDto);

    }

    @GetMapping(path = "/opinion/{userId}")
    public List<OpinionGetDto> getOpinionByUserId(@PathVariable String userId)  {

        return  userService.getOpinionByUserId(userId);
    }

}
