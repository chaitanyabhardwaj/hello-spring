package com.example.MyRest.controller;

import com.example.MyRest.service.Animal;
import com.example.util.MyRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyRestController {

    //inject values
    @Value("${park.name}")
    private String parkName;

    @Value("${park.country}")
    private String country;

    @Value("${park.establishmentYear}")
    private String establishmentYear;

    private Animal animal;

    @Autowired
    private MyRestUtil util;

    public MyRestController(Animal animal) {
        this.animal = animal;
    }

    @GetMapping("/test")
    public String test() {
        return "It's working!";
    }

    @GetMapping("/animalFarm")
    public String animalFarm(
            @RequestParam(name = "animal1") String animal1,
            @RequestParam(name = "animal2") String animal2) {
        return util.animalFarmHelper(animal1, animal2);
    }

    @GetMapping("/suggestAnimal")
    public String suggestAnimal() {
        List<String> animalList = List.of("Dog", "Cat", "Bird", "Tiger", "Elephant", "Camel", "Donkey", "Lizard");
        int i = ((int)Math.floor(Math.random() * 10)) % 8;
        return animalList.get(i);
    }

    @GetMapping("/parkDetails")
    public Map<String, String> parkDetails() {
        Map<String,String> parkDetailsMap = new HashMap<>();
        parkDetailsMap.put("Park Name", parkName);
        parkDetailsMap.put("Country", country);
        parkDetailsMap.put("Establishment Year", establishmentYear);
        return parkDetailsMap;
    }

    @GetMapping("/favAnimal")
    public String getFavAnimal() {
        return animal.getName();
    }

}
