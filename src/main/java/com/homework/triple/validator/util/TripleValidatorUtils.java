package com.homework.triple.validator.util;

import com.homework.triple.component.MessageComponent;
import com.homework.triple.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * 공통 validator util
 */
@Component
@RequiredArgsConstructor
public class TripleValidatorUtils {

    private final MessageComponent messageComponent;

    public void rejectIfEmptyOrWhitespace(Errors errors, String field) {
        Object value = errors.getFieldValue(field);
        if(value == null || !StringUtils.hasText(value.toString())|| !StringUtils.hasLength(value.toString().trim())) {
            errors.rejectValue(field, ErrorCode.REQUIRED_FIELD_EMPTY.toString(), null, messageComponent.getMessage(ErrorCode.REQUIRED_FIELD_EMPTY.getErrorCode()));
        }
    }


}
