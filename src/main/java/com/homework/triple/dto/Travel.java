package com.homework.triple.dto;

import lombok.*;

import java.sql.Timestamp;

// 필수 속성 : 도시, 여행 기간
// 추가 속성 : 조회 날짜, 여행 id, 여행 스타일 id
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
    private Integer travelId;
    private String userId;
    private Timestamp travelStartDateTime;
    private Timestamp travelEndDateTime;
    private Timestamp viewedDateTime;
    private Integer travelStyleId;
}
