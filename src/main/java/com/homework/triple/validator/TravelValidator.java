package com.homework.triple.validator;

import com.homework.triple.dto.Travel;
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
        Travel travel = (Travel) target;
        // 여행 시작 날짜가 종료 날짜보다 이전인지 확인
        if (!travel.getStartDateTime().before(travel.getEndDateTime())) {
            errors.rejectValue("startDateTime", ErrorCode.START_DATE_IS_NOT_BEFORE_THE_END_DATE.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.START_DATE_IS_NOT_BEFORE_THE_END_DATE.getErrorCode()), null, Locale.getDefault()));
        }
        // 여행 종료 날짜가 미래인지 확인
        if (!travel.getEndDateTime().after(new Timestamp(new Date().getTime()))) {
            errors.rejectValue("endDateTime", ErrorCode.END_DATE_IS_NOT_FUTURE.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.END_DATE_IS_NOT_FUTURE.getErrorCode()), null, Locale.getDefault()));
        }
    }

}
