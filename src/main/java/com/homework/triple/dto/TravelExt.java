package com.homework.triple.dto;

import lombok.Getter;

import java.util.List;

// 여행은 도시 목록을 가져야 한다.
// 하지만 db 설계는 여행 테이블, 여행-도시 테이블 (중간테이블), 도시 테이블로 설계되어 있다.
// 따라서 여행 등록 api 에서 사용할 travelExt를 만든다.
@Getter
public class TravelExt extends Travel {
    private List<Integer> cityList;
}
