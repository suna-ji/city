package com.homework.triple.mapper;

import com.homework.triple.dto.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * 도시 Mapper
 */
@Mapper
public interface CityMapper {

    City select(Integer cityId);

    int insert(City city);

    int update(City city);

    int delete(String cityId);

}
