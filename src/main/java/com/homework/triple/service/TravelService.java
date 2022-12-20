package com.homework.triple.service;

import com.homework.triple.dto.City;
import com.homework.triple.dto.Travel;
import com.homework.triple.mapper.TravelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelMapper travelMapper;
    public Travel findById(Integer travelId) {
        return travelMapper.select(travelId);
    }

    public int add(Travel travel) {
        if (travel == null) {
            return 0;
        }
        return travelMapper.insert(travel);
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
