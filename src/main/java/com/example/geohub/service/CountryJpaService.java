package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryRepository;
import com.example.geohub.repository.CountryJpaRepository;

@Service
public class CountryJpaService implements CountryRepository {
    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<Country> getCountries() {
        List<Country> countryList = countryJpaRepository.findAll();
        ArrayList<Country> countries = new ArrayList<>(countryList);
        return countries;
    }

    @Override
    public Country getCountryById(int countryId) {
        try {
            Country country = countryJpaRepository.findById(countryId).get();
            return country;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country addCountry(Country country) {
        countryJpaRepository.save(country);
        return country;
    }

    @Override
    public Country updateCountry(int countryId, Country country) {
        try {
            Country newCountry = countryJpaRepository.findById(countryId).get();
            if (country.getCountryName() != null) {
                newCountry.setCountryName(country.getCountryName());
            }
            if (country.getPopulation() != null) {
                newCountry.setPopulation(country.getPopulation());
            }
            if (country.getCurrency() != null) {
                newCountry.setCurrency(country.getCurrency());
            }
            if (country.getLatitude() != null) {
                newCountry.setLatitude(country.getLatitude());
            }
            if (country.getLongitude() != null) {
                newCountry.setLongitude(country.getLongitude());
            }
            countryJpaRepository.save(newCountry);
            return newCountry;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId) {
        if (countryJpaRepository.existsById(countryId)) {
            countryJpaRepository.deleteById(countryId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}