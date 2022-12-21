package com.homework.triple.mapper;

import com.homework.triple.dto.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 도시 Mapper
 */
@Mapper
public interface CityMapper {

    City select(Integer cityId);

    int insert(City city);

    int update(City city);

    int updateViewedDateTime(@Param("cityId") Integer cityId);

    int delete(@Param("cityId") Integer cityId);


}
