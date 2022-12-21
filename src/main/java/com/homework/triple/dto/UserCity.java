package com.homework.triple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCity {
    private String userId;
    private Integer cityId;
    private Timestamp viewedDateTime;
}
