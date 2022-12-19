package com.homework.triple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// 필수 속성 : 도시, 여행 기간
// 추가 속성 : 여행 id, 여행 스타일 id
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
    private Integer travelId;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Integer travelStyleId;
}
