package com.homework.triple.mapper;

import com.homework.triple.dto.CityTravel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityTravelMapper {

    int insert(CityTravel cityTravel);
}
