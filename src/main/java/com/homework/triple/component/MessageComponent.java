package com.homework.triple.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageComponent {

    private final MessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, null);
        } catch (NoSuchMessageException e) {

        }
        return code;
    }


}
