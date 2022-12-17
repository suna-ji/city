package com.homework.triple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// 필수 속성 : 도시 이름
// 추가 속성 : 국가, 도시 id, 생성 날짜, 업데이트 날짜, 조회 날짜
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer cityId;
    private String cityName;
    private Integer countryId;
    private Timestamp insertDateTime;
    private Timestamp updateDateTime;
    private Timestamp viewedDateTime;
}
