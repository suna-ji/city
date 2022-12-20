package com.homework.triple.mapper;

import com.homework.triple.dto.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TravelMapper {

    Travel select(Integer travelId);

    int insert(Travel travel);

    int update(Travel travel);

    int delete(@Param("travelId") Integer travelId);

}
