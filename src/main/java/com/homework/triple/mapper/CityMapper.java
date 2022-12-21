package com.homework.triple.mapper;

import com.homework.triple.dto.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 도시 Mapper
 */
@Mapper
public interface CityMapper {

    int insert(City city);

    List<City> select();

    City selectByCityId(Integer cityId);

    List<City> selectTravelingCity(@Param("userId") String userId);

    List<City> selectCityToTravel(@Param("userId") String userId);

    List<City> selectCityRegisteredToday();

    List<City> selectCityViewedLastWeek();

    int update(City city);

    int delete(@Param("cityId") Integer cityId);


}
