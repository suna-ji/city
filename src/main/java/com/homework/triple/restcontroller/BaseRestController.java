package com.homework.triple.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collection;
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
     * 목록 조회
     *
     * @param list
     * @return
     */
    protected ResponseEntity<Object> list(Collection<?> list) {
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }


    /**
     * 목록 조회 실패
     *
     * @param object
     * @param errors
     * @return
     */
    protected ResponseEntity<List<String>> list(Object object, Errors errors) {
        return error(object, errors);
    }

    /**


     /**
     * 수정 성공
     * @param count
     * @return
     */
    protected ResponseEntity<Object> modify(int count, Object object) {
        if (count <= 0) { // update된 행의 개수
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(object, HttpStatus.OK);
    }

    /**
     * 데이터 조회
     * @return
     */
    protected ResponseEntity<Object> data(Object data) {
        if (data == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<Object>(data, HttpStatus.OK); // 200
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
