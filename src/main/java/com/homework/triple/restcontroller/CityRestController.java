package com.homework.triple.restcontroller;

import com.homework.triple.dto.City;
import com.homework.triple.service.CityService;
import com.homework.triple.validator.CityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 도시 REST Controller
 */
@RestController
@RequestMapping("/api/{version}/city")
@RequiredArgsConstructor
public class CityRestController extends BaseRestController {

    private final CityService cityService;
    private final CityValidator cityValidator;

    // list
    // details
    // save
    // add
    // modify
    // remove

    /**
     * 도시 등록 API
     */
    @PostMapping("")
    public ResponseEntity add(@PathVariable String version, @RequestBody City city, BindingResult bindingResult) {
        cityValidator.validateAdd(city, bindingResult);
        if (bindingResult.hasErrors()) {
            return add(city, bindingResult);
        }
        int count = cityService.add(city);
        return add(count, cityService.findById(city.getCityId()));
    }

}
