package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.geohub.model.*;
import com.example.geohub.repository.*;

@Service
public class CityJpaService implements CityRepository {
    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Override
    public ArrayList<City> getCities() {
        List<City> cityList = cityJpaRepository.findAll();
        ArrayList<City> cities = new ArrayList<>(cityList);
        return cities;
    }

    @Override
    public City getCityById(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public City addCity(City city) {
        Country country = city.getCountry();
        int countryId = country.getCountryId();
        Country newCountry = countryJpaRepository.findById(countryId).get();
        city.setCountry(newCountry);
        cityJpaRepository.save(city);
        return city;
    }

    @Override
    public City updateCity(int cityId, City city) {
        try {
            City newCity = cityJpaRepository.findById(cityId).get();
            if (city.getCityName() != null) {
                newCity.setCityName(city.getCityName());
            }
            if (city.getPopulation() != null) {
                newCity.setPopulation(city.getPopulation());
            }
            if (city.getLatitude() != null) {
                newCity.setLatitude(city.getLatitude());
            }
            if (city.getLongitude() != null) {
                newCity.setLongitude(city.getLongitude());
            }
            if (city.getCountry() != null) {
                Country country = city.getCountry();
                int countryId = country.getCountryId();
                try {
                    Country newCountry = countryJpaRepository.findById(countryId).get();
                    newCity.setCountry(newCountry);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            }
            cityJpaRepository.save(newCity);
            return newCity;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCity(int cityId) {
        if (cityJpaRepository.existsById(cityId)) {
            cityJpaRepository.deleteById(cityId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country getCountryByCityId(int cityId) {
        City city = cityJpaRepository.findById(cityId).get();
        Country country = city.getCountry();
        return country;
    }
}