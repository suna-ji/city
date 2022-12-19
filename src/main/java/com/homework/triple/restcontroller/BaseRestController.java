package com.homework.triple.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BaseRestController {

    /**
     * 추가 성공
     *
     * @param count
     * @param object
     * @return
     */
    protected ResponseEntity<Object> add(int count, Object object) {
        if (count <= 0) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(object, HttpStatus.CREATED);
    }

    /**
     * 추가 실패
     *
     * @param object
     * @param errors
     * @return
     */
    protected ResponseEntity<List<String>> add(Object object, Errors errors) {
        return error(object, errors);
    }

    /**
     * 수정 성공
     * @param count
     * @param updatedObject
     * @param returnObject
     * @return
     */
    protected ResponseEntity<Object> modify(int count, Object object) {
        if (count <= 0) { // update된 행의 개수
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(object, HttpStatus.OK);
    }

    /**
     * 수정 실패
     *
     * @param object
     * @param errors
     * @return
     */
    protected ResponseEntity<List<String>> modify(Object object, Errors errors) {
        return error(object, errors);
    }


    /**
     * 삭제 성공
     *
     * @param count
     * @return
     */
    protected ResponseEntity<Object> delete(int count) {
        if (count <= 0) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * 삭제 실패
     *
     * @param object
     * @param errors
     * @return
     */
    protected ResponseEntity<List<String>> delete(Object object, Errors errors) {
        return error(object, errors);
    }


    private ResponseEntity<List<String>> error(Object object, Errors errors) {
        List<String> errorMessages = new ArrayList<String>();
        for (FieldError fieldError : errors.getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage() + " : " + fieldError.getField());
        }
        return new ResponseEntity<List<String>>(errorMessages, HttpStatus.BAD_REQUEST); // 400
    }

}
