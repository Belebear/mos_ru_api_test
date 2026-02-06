import models.statusCard.PostErrorResponseCheckStatusCardWithObjectModel;
import models.statusCard.PostErrorResponseCheckStatusCardWithoutObjectModel;
import models.statusCard.PostRequestCheckStatusCardModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.Endpoints;
import utils.TestData;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("checkStatusCardTests")
public class CheckStatusCardTest extends TestBase {

    @Test
    @DisplayName("Отправка POST запроса feedback без тела запроса")
    void postCheckStatusCardWithInvalidCaptchaTest() {
        PostErrorResponseCheckStatusCardWithObjectModel response = step("Отправка запроса на проверку статуса карты с неверным captcha_code", () -> given()
                .spec(RequestSpec.baseSpec()))
                .header("captcha-code", TestData.headerCaptcha)
                .body("{}")
                .when()
                .post(Endpoints.CHECKCARD)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseCheckStatusCardWithObjectModel.class);

        step("Проверка полей code, message, card_series, card_number, number_d_u_l, и captcha в ответе", () -> {
            assertEquals("VALIDATION_FAILED", response.getCode());
            assertEquals("Validation failed", response.getMessage());
            assertEquals("Введите серию карты", response.getErrors().getCardSeries());
            assertEquals("Введите номер карты", response.getErrors().getCardNumber());
            assertEquals("Введите номер документа удостоверяющего личность", response.getErrors().getNumberDUL());
            assertEquals("Введите проверочный код", response.getErrors().getCaptcha());
        });

    }

    @Test
    @DisplayName("Отправка POST запроса feedback без captcha-code header")
    void postCheckStatusCardWithoutCaptchaHeaderTest() {
        PostErrorResponseCheckStatusCardWithoutObjectModel response = step("Отправка запроса на проверку статуса карты с неверным captcha_code", () -> given()
                .spec(RequestSpec.baseSpec()))
                .body("{}")
                .when()
                .post(Endpoints.CHECKCARD)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseCheckStatusCardWithoutObjectModel.class);

        step("Проверка полей code, message, card_series, card_number, number_d_u_l, и captcha в ответе", () -> {
            assertEquals("BAD_REQUEST", response.getCode());
            assertEquals("Expected header 'captcha-code not found in request", response.getMessage());
        });
    }


    @Test
    @DisplayName("Отправка POST запроса feedback с неверным форматом поля card_series")
    void postCheckStatusCardWithInvalidCardSeriasTest() {
        PostRequestCheckStatusCardModel requestBody = step("Формирование тела запроса", () -> PostRequestCheckStatusCardModel.builder()
                .card_number(TestData.getCardNubmer())
                .card_series(TestData.getCardSeries())
                .captcha("123")
                .numberDul(TestData.getCardSeries())
                .build());
        PostErrorResponseCheckStatusCardWithObjectModel response = step("Отправка запроса на проверку статуса карты с неверным captcha_code", () -> given()
                .spec(RequestSpec.baseSpec()))
                .header("captcha-code", TestData.headerCaptcha)
                .body(requestBody)
                .when()
                .post(Endpoints.CHECKCARD)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseCheckStatusCardWithObjectModel.class);

        step("Проверка полей code, message, card_series, card_number, number_d_u_l, и captcha в ответе", () -> {
            assertEquals("VALIDATION_FAILED", response.getCode());
            assertEquals("Validation failed", response.getMessage());
            assertEquals("Серия не соответствует формату - 00/00 00", response.getErrors().getCardSeries());
        });

    }
}

