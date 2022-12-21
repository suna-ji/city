package com.homework.triple.mapper;

import com.homework.triple.dto.UserCity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCityMapper {

    List<UserCity> select(UserCity userCity);

    int insert(UserCity userCity);

    int update(UserCity userCity);
}
