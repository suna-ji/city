package com.homework.triple;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Data를 cleanup하여 테스트를 격리하는 방법
 * 1. @DirtiesContext 어노테이션 이용 (오래 걸림)
 * 2. Repository로 초기화 (테스트가 특정 Repository와 강하게 결합됨)
 * 3. @Transcational (RestAssured에서는 사용불가)
 * 4. 쿼리를 이용한 초기화
 *
 * 과제에서는 테스트 개수가 적기 때문에 DirtiesContext를 사용하여 테스트를 격리하였습니다.
 */
@DisplayName("API 검증 테스트 (테스트 격리 O)")
@ActiveProfiles("development")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RefactoredAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
    }

    @DisplayName("하노이라는 도시를 등록한다.")
    @Test
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

    @DisplayName("도시이름을 넣지 않고 도시를 등록하면 실패한다.")
    @Test
    void addCityWithEmptyCityName() {
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "");
        city.put("countryId", 1);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(400)
                    .log().all();
    }

    @DisplayName("cityName으로 호치민 도시를 등록한 후, countryId를 1로 변경한다.")
    @Test
    void modifyCity() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "호치민");
        city.put("countryId", 2);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("호치민"))
                    .log().all();

        // 도시 수정
        Map<String, Object> modifyCity = new HashMap<>();
        modifyCity.put("countryId", 1);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(modifyCity)
                .when()
                    .put("/api/1/city/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("countryId", equalTo(1))
                    .log().all();
    }

    @DisplayName("발리를 등록한 후 조회해본다.")
    @Test
    void detailsCity() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "발리");
        city.put("countryId", 2);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("발리"))
                    .assertThat().body("countryId", equalTo(2))
                    .log().all();
        // 도시 조회
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/1/city/1/testUser")
                .then()
                    .statusCode(200)
                    .assertThat().body("cityName", equalTo("발리"))
                    .assertThat().body("countryId", equalTo(2))
                    .log().all();
    }

    @DisplayName("파리를 도시로 등록하고, 파리여행을 등록한다.")
    @Test
    void addTravelSuccess() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();

        // 여행 등록
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

    @DisplayName("여행종료날짜에 과거를 넣으면 여행 등록에 실패한다.")
    @Test
    void addTravelFail() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();

        // 여행 등록
        Map<String, Object> travel = new HashMap<>();
        travel.put("travelStartDateTime", "2021-11-21");
        travel.put("travelEndDateTime", "2022-03-14");
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
                    .statusCode(400)
                    .log().all();
    }

    @DisplayName("여행스타일을 설정하기 위해서 여행을 수정한다.")
    @Test
    void modifyTravel() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();

        // 여행등록
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
                    .log().all();

        // 여행 수정
        Map<String, Object> modifyTravel = new HashMap<>();
        modifyTravel.put("travelStyleId", 1);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(modifyTravel)
                .when()
                    .put("/api/1/travel/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("userId", equalTo("testUser"))
                    .assertThat().body("travelStyleId", equalTo(1))
                    .log().all();
    }

    @DisplayName("여행을 조회해본다.")
    @Test
    void detailsTravel() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();
        // 여행 등록
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
                    .log().all();

        // 여행 조회
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
    void listCity() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();
        // 여행등록 (여행중인 도시는 항상 도시 목록에 포함)
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
                    .log().all();

        // 도시 목록 조회
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
    void deleteTravel() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();

        // 여행 등록
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
        // 여행 삭제
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
    void deleteCity() {
        // 도시 등록
        Map<String, Object> city = new HashMap<>();
        city.put("cityName", "파리");
        city.put("countryId", 3);
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(city)
                .when()
                    .post("/api/1/city")
                .then()
                    .statusCode(201)
                    .assertThat().body("cityName", equalTo("파리"))
                    .assertThat().body("countryId", equalTo(3))
                    .log().all();
        // 도시 삭제
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
