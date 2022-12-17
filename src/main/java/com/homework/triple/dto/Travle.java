package com.homework.triple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

// 필수 속성 : 도시(리스트), 여행 기간
// 추가 속성 : 여행 id, 여행 스타일 id
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Travle {
    private Integer travleId;
    private List<City> cityList;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Integer travleStyleId;
}
