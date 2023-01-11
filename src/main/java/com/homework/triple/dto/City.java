package com.homework.triple.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

// 필수 속성 : 도시 이름
// 추가 속성 : 국가, 도시 id, 생성 날짜, 업데이트 날짜, 조회 날짜
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer cityId;
    private String cityName;
    private Integer countryId;
    private Timestamp insertDateTime;
    private Timestamp updateDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
