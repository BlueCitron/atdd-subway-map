package nextstep.subway.line;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.line.dto.LineRequest;
import org.apache.groovy.util.Maps;
import org.springframework.http.MediaType;

import java.util.Map;

import static java.lang.String.valueOf;

public class LineSteps {

    public static ExtractableResponse<Response> 지하철_노선_생성(LineRequest lineRequest) {
        Map<String, String> params = Maps.of(
                "name", lineRequest.getName()
                , "color", lineRequest.getColor()
                , "upStationId", valueOf(lineRequest.getUpStationId())
                , "downStationId", valueOf(lineRequest.getDownStationId())
                , "distance", valueOf(lineRequest.getDistance()));
        return RestAssured
                .given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/lines")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 지하철_노선_목록_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/lines")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 지하철_노선_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/lines/1")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 지하철_노선_수정_요청(Map<String, String> params) {
        return RestAssured
                .given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/lines/1")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 지하철_노선_제거_요청(Long lineId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/lines/{lineId}" + lineId)
                .then().log().all().extract();
    }
    
}
