package com.example.geohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.geohub.model.*;
import com.example.geohub.service.CityJpaService;

@RestController
public class CityController {
    @Autowired
    public CityJpaService cityService;

    @GetMapping("/countries/cities")
    public ArrayList<City> getCities() {
        return cityService.getCities();
    }

    @GetMapping("/countries/cities/{cityId}")
    public City getCityById(@PathVariable("cityId") int cityId) {
        return cityService.getCityById(cityId);
    }

    @GetMapping("/cities/{cityId}/country")
    public Country getCountryByCityId(@PathVariable("cityId") int cityId) {
        return cityService.getCountryByCityId(cityId);
    }

    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable("cityId") int cityId, @RequestBody City city) {
        return cityService.updateCity(cityId, city);
    }

    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable("cityId") int cityId) {
        cityService.deleteCity(cityId);
    }
}