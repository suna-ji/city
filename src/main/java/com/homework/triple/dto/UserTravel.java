package com.homework.triple.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserTravel {
    private String userId;
    private Integer travelId;
}
