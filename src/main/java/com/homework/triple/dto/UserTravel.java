package com.homework.triple.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTravel {
    private Integer userId;
    private Integer travelId;
}
