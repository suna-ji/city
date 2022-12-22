package com.homework.triple.restcontroller;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 8) 여행을 삭제한다.
 * 9) 도시를 삭제한다.
 */
@DisplayName("API 검증 테스트")
@ActiveProfiles("development")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void addCity() {
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

    @DisplayName("호치민으로 도시이름을 변경한다.")
    @Test
    @Order(2)
    void modifyCity() {
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "호치민");
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .put("/api/1/city/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("cityName", equalTo("호치민"))
                    .log().all();
    }

    @DisplayName("호치민으로 수정이 잘 되었는지 도시를 조회해본다.")
    @Test
    @Order(3)
    void detailsCity() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/1/city/1/testUser")
                .then()
                    .statusCode(200)
                    .assertThat().body("cityName", equalTo("호치민"))
                    .log().all();
    }

    @DisplayName("호치민을 도시로 지정해서 여행을 등록한다.")
    @Test
    @Order(4)
    void addTravel() {
        Map<String, Object> travel = new HashMap<>();
        travel.put("travelStartDateTime", "2022-11-21");
        travel.put("travelEndDateTime", "2023-03-14");
        travel.put( "userId", "testUser");
        List<Integer> cityList = new ArrayList<>();
        cityList.add(1);
        travel.put("cityList", cityList);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(travel)
                .when()
                    .post("/api/1/travel")
                .then()
                    .statusCode(201)
                    .assertThat().body("userId", equalTo("testUser"))
                    .log().all();
    }

    @DisplayName("여행스타일을 설정하기 위해서 여행을 수정한다.")
    @Test
    @Order(5)
    void modifyTravel() {
        Map<String, Object> travel = new HashMap<>();
        travel.put("travelStyleId", 1);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(travel)
                .when()
                    .put("/api/1/travel/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("userId", equalTo("testUser"))
                    .assertThat().body("travelStyleId", equalTo(1))
                    .log().all();
    }

    @DisplayName("여행스타일이 설정되었는지 여행을 조회해본다.")
    @Test
    @Order(6)
    void detailsTravel() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/1/travel/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("userId", equalTo("testUser"))
                    .assertThat().body("travelStyleId", equalTo(1))
                    .log().all();
    }

    @DisplayName("메인페이지로 가서 도시 목록을 확인한다.")
    @Test
    @Order(7)
    void listCity() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/1/city/user/testUser")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @DisplayName("여행을 삭제한다.")
    @Test
    @Order(8)
    void deleteTravel() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .delete("/api/1/travel/1")
                .then()
                    .statusCode(204)
                    .log().all();
    }

    @DisplayName("도시도 삭제한다.")
    @Test
    @Order(9)
    void deleteCity() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .delete("/api/1/city/1")
                .then()
                    .statusCode(204)
                    .log().all();
    }



}