package com.homework.triple.service;

import com.homework.triple.dto.City;
import com.homework.triple.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityMapper cityMapper;

    public City findById(Integer cityId) {
        return cityMapper.select(cityId);
    }

    public int add(City city) {
        if (city == null) {
            return 0;
        }
        return cityMapper.insert(city);
    }

    public int modify(City city) {
        if (city == null) {
            return 0;
        }
        return cityMapper.update(city);
    }

    public int remove(Integer cityId) {
        return cityMapper.delete(cityId);
    }




}
