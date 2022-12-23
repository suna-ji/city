package com.homework.triple.validator;

import com.homework.triple.dto.City;
import com.homework.triple.exception.ErrorCode;
import com.homework.triple.mapper.CityTravelMapper;
import com.homework.triple.validator.util.TripleValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

/**
 * city Validator
 */
@Component
@RequiredArgsConstructor
public class CityValidator implements Validator {

    private final TripleValidatorUtils tripleValidatorUtils;
    private final CityTravelMapper cityTravelMapper;
    private final MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validateAdd(Object target, Errors errors) {
        tripleValidatorUtils.rejectIfEmptyOrWhitespace(errors, "cityName");
        tripleValidatorUtils.rejectIfEmptyOrWhitespace(errors, "countryId");
    }

    public void validateDelete(City city, Errors errors) {
        // 해당 도시가 지정된 여행이 없는지 확인
        if (cityTravelMapper.selectCityCount(city.getCityId()) > 0) {
            errors.rejectValue("cityId", ErrorCode.UNABLE_TO_DELETE_CITY_WITH_THE_TRAVEL.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.UNABLE_TO_DELETE_CITY_WITH_THE_TRAVEL.getErrorCode()), null, Locale.getDefault()));
        }
    }
}
