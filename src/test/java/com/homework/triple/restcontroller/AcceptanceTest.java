package com.homework.triple.restcontroller;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

/**
 * 인수 테스트 시나리오
 * 1) 도시를 등록한다.
 * 2) 도시를 수정한다.
 * 3) 도시가 잘 수정됐는지 확인한다. (단일 도시 조회)
 * 4) 등록된 도시로 여행을 등록한다.
 * 5) 여행을 수정한다.
 * 6) 여행이 잘 수정됐는지 확인한다. (단일 여행 조회)
 * 7) 사용자 별 도시 목록을 조회한다.
 * 8) 도시를 삭제한다.
 * 9) 여행을 삭제한다.
 */
@DisplayName("도시 관리 API 검증 테스트")
@ActiveProfiles("development")
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
    }

    @DisplayName("하노이라는 도시를 등록한다.")
    @Test
    void addCity() throws Exception {
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "하노이");
        city.put("countryId", 1);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("하노이"))
                    .log().all();
    }

}