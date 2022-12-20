package com.homework.triple.service;

import com.homework.triple.dto.Travel;
import com.homework.triple.dto.UserTravel;
import com.homework.triple.mapper.TravelMapper;
import com.homework.triple.mapper.UserTravelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelMapper travelMapper;
    private final UserTravelMapper userTravelMapper;

    public Travel findById(Integer travelId) {
        return travelMapper.select(travelId);
    }

    public int add(Travel travel) {
        if (travel == null) {
            return 0;
        }
        if (travelMapper.insert(travel) <= 0 ) {
            return 0;
        }
        UserTravel userTravel = UserTravel.builder()
                .userId(travel.getUserId())
                .travelId(travel.getTravelId())
                .build();
        return userTravelMapper.insert(userTravel);
    }

    public int modify(Travel travel) {
        if (travel == null) {
            return 0;
        }
        return travelMapper.update(travel);
    }

    public int remove(Integer travelId) {
        return travelMapper.delete(travelId);
    }

}
