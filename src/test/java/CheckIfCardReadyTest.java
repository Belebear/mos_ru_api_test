package Test;

import models.readyCard.PostErrorResponseWithObjectCheckIfCardReadyModel;
import models.readyCard.PostRequestCheckIfCardReadyModel;
import models.readyCard.PostErrorResponseWithoutObjectCheckIfCardReadyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.Endpoints;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("checkIfCardReadyTests")
public class CheckIfCardReadyTest extends TestBase {

    @Test
    @DisplayName("Возвращение ошибки при запросе с отсутствующим captcha-code в header")
    void postWithoutCaptchaCodeInHeader() {
        PostRequestCheckIfCardReadyModel requestBody = step("Создание тела запроса", () -> PostRequestCheckIfCardReadyModel.builder()
                .blank_no("1231231")
                .captcha("123")
                .doc_no("123")
                .build());
        PostErrorResponseWithoutObjectCheckIfCardReadyModel response = step("Отправка POST запроса с невалидными данными", () -> given()
                .spec(RequestSpec.baseSpec())
                .body(requestBody)
                .when()
                .post(Endpoints.ORDERSTATE)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseWithoutObjectCheckIfCardReadyModel.class));
        assertEquals("BAD_REQUEST", response.getCode());
        assertEquals("Expected header 'captcha-code not found in request", response.getMessage());
    }

    @Test
    @DisplayName("Возвращение ошибки при запросе с неверным captcha-code в header")
    void postWithInvalidCaptchaCode() {
        PostRequestCheckIfCardReadyModel requestBody = step("Создание тела запроса", () -> PostRequestCheckIfCardReadyModel.builder()
                .blank_no("1231231")
                .captcha("123")
                .doc_no("123")
                .build());
        PostErrorResponseWithObjectCheckIfCardReadyModel response = step("Отправка POST запроса с невалидными данными", () -> given()
                .spec(RequestSpec.baseSpec())
                .header("captcha-code", "018d2337fc5adcfeb237499d71dbf321")
                .body(requestBody)
                .when()
                .post(Endpoints.ORDERSTATE)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseWithObjectCheckIfCardReadyModel.class));
        assertEquals("VALIDATION_FAILED", response.getCode());
        assertEquals("Validation failed", response.getMessage());
        assertEquals("Введенные вами символы не совпадают с проверочным кодом", response.getErrors().getCaptcha());
    }
}
