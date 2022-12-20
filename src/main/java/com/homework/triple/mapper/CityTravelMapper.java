package com.homework.triple.mapper;

import com.homework.triple.dto.CityTravel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityTravelMapper {

    int insert(CityTravel cityTravel);

    int update(CityTravel cityTravel);

    int delete(@Param("travelId") Integer travelId);
}
