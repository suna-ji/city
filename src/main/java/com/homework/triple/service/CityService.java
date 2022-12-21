package com.homework.triple.service;

import com.homework.triple.dto.City;
import com.homework.triple.dto.UserCity;
import com.homework.triple.mapper.CityMapper;
import com.homework.triple.mapper.UserCityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityMapper cityMapper;
    private final UserCityMapper userCityMapper;

    public City findById(Integer cityId) {
        return cityMapper.selectByCityId(cityId);
    }

    public City findByIdWithUpdateView(Integer cityId, String userId) {
        UserCity userCity = UserCity.builder()
                .userId(userId)
                .cityId(cityId)
                .build();
        if (userCityMapper.select(userCity).size() != 0) {
            userCityMapper.update(userCity);
        } else {
            userCityMapper.insert(userCity);
        }
        return cityMapper.selectByCityId(cityId);
    }

    public List<City> findByUserId(String userId) {

        List<City> resultCityList = new ArrayList<>();

        // 여행중인 도시
        resultCityList.addAll(cityMapper.selectTravelingCity(userId));

        // 10개로 제한이 있는 도시 리스트
        List<City> userCityList = new ArrayList<>();
        // 여행이 예정된 도시
        userCityList.addAll(cityMapper.selectCityToTravel(userId));
        // 하루 이내에 등록된 도시 (가장 최근 등록 순)
        userCityList.addAll(cityMapper.selectCityRegisteredToday());
        // 최근 일주일 이내에 한번 이상 조회된 도시 (가장 최근 조회 순)
        userCityList.addAll(cityMapper.selectCityViewedLastWeek());
        // 나머지
        userCityList.addAll(cityMapper.select());

        if (userCityList.size() <= 10) {
            resultCityList.addAll(userCityList);
            return resultCityList;
        }

        List<City> userCityListWithLimit = new ArrayList<>(userCityList.subList(0, 10));
        resultCityList.addAll(userCityListWithLimit);
        return resultCityList;
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
