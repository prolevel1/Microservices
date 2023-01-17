package com.UserServices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<List<String>> getName() {
        List<String> list = Arrays.asList("Ram", "Shhs", "ansh");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
