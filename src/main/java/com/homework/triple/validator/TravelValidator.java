package com.homework.triple.validator;

import com.homework.triple.dto.TravelExt;
import com.homework.triple.exception.ErrorCode;
import com.homework.triple.validator.util.TripleValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TravelValidator implements Validator {

    private final TripleValidatorUtils tripleValidatorUtils;
    private final MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validateAdd(Object target, Errors errors) {
        TravelExt travel = (TravelExt) target;
        // 여행 시작 날짜가 종료 날짜보다 이전인지 확인
        if (!travel.getTravelStartDateTime().before(travel.getTravelEndDateTime())) {
            errors.rejectValue("travelStartDateTime", ErrorCode.START_DATE_IS_NOT_BEFORE_THE_END_DATE.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.START_DATE_IS_NOT_BEFORE_THE_END_DATE.getErrorCode()), null, Locale.getDefault()));
        }
        // 여행 종료 날짜가 미래인지 확인
        if (!travel.getTravelEndDateTime().after(new Timestamp(new Date().getTime()))) {
            errors.rejectValue("travelEndDateTime", ErrorCode.END_DATE_IS_NOT_FUTURE.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.END_DATE_IS_NOT_FUTURE.getErrorCode()), null, Locale.getDefault()));
        }
        // 사용자 id가 있는지 확인
        tripleValidatorUtils.rejectIfEmptyOrWhitespace(errors, "userId");
        // 최소 1개 도시가 있어야함
        if (travel.getCityList() == null || travel.getCityList().isEmpty()) {
            errors.rejectValue("cityList", ErrorCode.CITY_LIST_IS_EMPTY.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.CITY_LIST_IS_EMPTY.getErrorCode()), null, Locale.getDefault()));
        }
    }

}
