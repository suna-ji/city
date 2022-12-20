package com.homework.triple.service;

import com.homework.triple.dto.CityTravel;
import com.homework.triple.dto.Travel;
import com.homework.triple.dto.TravelExt;
import com.homework.triple.dto.UserTravel;
import com.homework.triple.mapper.CityTravelMapper;
import com.homework.triple.mapper.TravelMapper;
import com.homework.triple.mapper.UserTravelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelMapper travelMapper;
    private final UserTravelMapper userTravelMapper;
    private final CityTravelMapper cityTravelMapper;

    public Travel findById(Integer travelId) {
        return travelMapper.select(travelId);
    }

    public int add(TravelExt travel) {
        if (travel == null) {
            return 0;
        }

        int count = travelMapper.insert(travel);

        if (count > 0) {
            // 여행자 등록
            UserTravel userTravel = UserTravel.builder()
                    .userId(travel.getUserId())
                    .travelId(travel.getTravelId())
                    .build();
            userTravelMapper.insert(userTravel);

            // 도시 등록
            for (Integer cityId : travel.getCityList()) {
                CityTravel cityTravel = CityTravel.builder()
                        .travelId(travel.getTravelId())
                        .cityId(cityId)
                        .build();
                cityTravelMapper.insert(cityTravel);
            }
        }
        return count;
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
