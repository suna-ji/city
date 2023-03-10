package com.homework.triple.mapper;

import com.homework.triple.dto.CityTravel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityTravelMapper {

    int selectCityCount(@Param("cityId") Integer cityId);

    int insert(CityTravel cityTravel);

    int delete(@Param("travelId") Integer travelId);
}
