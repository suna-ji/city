package com.homework.triple.mapper;

import com.homework.triple.dto.UserTravel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTravelMapper {

    int insert(UserTravel userTravel);
}
