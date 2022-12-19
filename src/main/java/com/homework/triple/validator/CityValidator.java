package com.homework.triple.validator;

import com.homework.triple.validator.util.TripleValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * city Validator
 */
@Component
@RequiredArgsConstructor
public class CityValidator implements Validator {

    private final TripleValidatorUtils tripleValidatorUtils;

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

    public void validateModify(Object target, Errors errors) {

    }

    public void validateDelete(Integer cityId) {
        // 해당 도시가 지정된 여행이 없는 지 확인
    }
}
