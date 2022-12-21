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


    /**
     * 도시 등록 API
     * @param version
     * @param city
     * @param bindingResult
     * @return
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

    /**
     * 단일 도시 조회 API
     * @param version
     * @param cityId
     * @return
     */
    @GetMapping("/{cityId}/{userId}")
    public ResponseEntity details(@PathVariable String version, @PathVariable Integer cityId, @PathVariable String userId) {
        return data(cityService.findByIdWithUpdateView(cityId, userId));
    }

    /**
     * 사용자 별 도시 목록 조회 API
     * 우선 순위
     * - 여행중인 도시 (여행 시작일이 빠른것부터)
     * - 여행이 예정된 도시 (여행 시작일이 빠른것부터)
     * - 하루 이내에 등록된 도시 (가장 최근에 등록된 것부터)
     * - 최근 일주일 이내에 한 번 이상 조회된 도시 (가장 최근에 조회한 것부터)
     * - 위의 조건이 해당되지 않는 모든 도시는 무작위로
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity list(@PathVariable String version, @PathVariable String userId) {
        return list(cityService.findByUserId(userId));
    }

    /**
     * 도시 수정 API
     * @param version
     * @param cityId
     * @param city
     * @param bindingResult
     * @return
     */
    @PutMapping("/{cityId}")
    public ResponseEntity modify(@PathVariable String version, @PathVariable Integer cityId, @RequestBody City city, BindingResult bindingResult) {
        city.setCityId(cityId);
        cityValidator.validate(city, bindingResult);
        if (bindingResult.hasErrors()) {
            return modify(city, bindingResult);
        }
        int count = cityService.modify(city);
        return modify(count, cityService.findById(city.getCityId()));
    }

    /**
     * 도시 삭제 API
     * @param version
     * @param cityId
     * @return
     */
    @DeleteMapping("/{cityId}")
    public ResponseEntity delete(@PathVariable String version, @PathVariable Integer cityId ) {
        cityValidator.validateDelete(cityId);
        int count = cityService.remove(cityId);
        return delete(count);
    }

}
