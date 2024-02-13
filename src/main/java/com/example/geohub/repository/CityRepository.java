package com.example.geohub.repository;

import com.example.geohub.model.*;
import java.util.ArrayList;

public interface CityRepository {
    ArrayList<City> getCities();

    City getCityById(int cityId);

    City addCity(City city);

    City updateCity(int cityId, City city);

    void deleteCity(int cityId);

    Country getCountryByCityId(int cityId);
}