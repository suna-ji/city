package com.homework.triple.restcontroller;

import com.homework.triple.dto.TravelExt;
import com.homework.triple.service.TravelService;
import com.homework.triple.validator.TravelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 여행 REST Controller
 */
@RestController
@RequestMapping("/api/{version}/travel")
@RequiredArgsConstructor
public class TravelRestController extends BaseRestController {

    private final TravelService travelService;
    private final TravelValidator travelValidator;

    /**
     * 여행 등록 API
     */
    @PostMapping
    public ResponseEntity add(@PathVariable String version, @RequestBody TravelExt travel, BindingResult bindingResult) {
        travelValidator.validateAdd(travel, bindingResult);
        if (bindingResult.hasErrors()) {
            return add(travel, bindingResult);
        }
        int count = travelService.add(travel);
        return add(count, travelService.findById(travel.getTravelId()));
    }

    /**
     * 여행 단일 조회 API
     * @param version
     * @param travelId
     * @return
     */
    @GetMapping("/{travelId}")
    public ResponseEntity details(@PathVariable String version, @PathVariable Integer travelId ) {
        return data(travelService.findById(travelId));
    }

    /**
     * 여행 수정 API
     * @param version
     * @param travelId
     * @param travel
     * @param bindingResult
     * @return
     */
    @PutMapping("/{travelId}")
    public ResponseEntity modify(@PathVariable String version, @PathVariable Integer travelId, @RequestBody TravelExt travel, BindingResult bindingResult) {
        travel.setTravelId(travelId);
        if (bindingResult.hasErrors()) {
            return modify(travel, bindingResult);
        }
        int count = travelService.modify(travel);
        return modify(count, travelService.findById(travel.getTravelId()));
    }

    /**
     * 여행 삭제 API
     * @param version
     * @param travelId
     * @return
     */
    @DeleteMapping("/{travelId}")
    public ResponseEntity delete(@PathVariable String version, @PathVariable Integer travelId ) {
        int count = travelService.remove(travelId);
        return delete(count);
    }

}
