package Test;

import models.statusCard.PostErrorResponseCheckStatusCardModel;
import models.statusCard.PostRequestCheckStatusCardModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.Endpoints;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("checkStatusCardTests")
public class CheckStatusCardTest extends TestBase {

    @Test
    @DisplayName("Отправка POST запроса на feedback без тела запроса")
    void postCheckStatusCardWithInvalidCaptchaTest() {
        PostRequestCheckStatusCardModel requestBody = step("Формирование тела запроса", () -> PostRequestCheckStatusCardModel.builder()
//                .card_number(TestData.getCardNubmer())
//                .card_series(TestData.getCardSeries())
//                .captcha("123")
//                .number_dul(TestData.getCardSeries())
                .build());

        PostErrorResponseCheckStatusCardModel response = step("Отправка запроса на проверку статуса карты с неверным captcha_code", () -> given()
                .spec(RequestSpec.baseSpec()))
                .body(requestBody)
                .when()
                .post(Endpoints.CHECKCARD)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseCheckStatusCardModel.class);
    }
}
