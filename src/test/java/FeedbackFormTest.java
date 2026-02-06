package Test;

import models.feedback.PostRequestFeedbackModel;
import models.feedback.PostErrorResponseFeedbackModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.Endpoints;
import utils.TestData;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("feedbackFormTests")
public class FeedbackFormTest extends TestBase {

    @Test
    @DisplayName("Отправка запроса на обратную связь без captcha_code")
    void postCheckStatusOfValidCardWithoutCaptchaCodeTest() {
        PostRequestFeedbackModel requestBody = step("Формирование тела запроса без captcha_code", () -> PostRequestFeedbackModel.builder()
                .message(TestData.getMessage())
                .phone(TestData.getPhoneNubmer())
                .captcha("")
                .cardNumber(TestData.getCardNubmer())
                .email(TestData.getEmail())
                .name(TestData.getName())
                .surname(TestData.getSurname())
                .captchaCode("0f4437ce75d6cee0a0117684268e20f2")
                .isLegalEntity(false)
                .files(List.of())
                .themeId(75661)
                .build());
        PostErrorResponseFeedbackModel response = step("Отправка POST запроса без captcha code в header", () -> given()
                .spec(RequestSpec.baseSpec()))
                .body(requestBody)
                .when()
                .post(Endpoints.FEEDBACK)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseFeedbackModel.class);
        PostErrorResponseFeedbackModel.ErrorData errorData = response.getData().get(0);

        assertEquals("EMPTY_CAPTCHA", errorData.getError_code());
        assertEquals("captcha", errorData.getField());
        assertEquals("Fields \"captcha\" or \"captchaCode\" is empty.", errorData.getMessage());

    }

    @Test
    @DisplayName("Отправка запроса на обратную связь с неверным captcha_code")
    void checkStatusOfValidCardWithInvalidCaptchaCodeTest() {
        PostRequestFeedbackModel requestBody = step("Формирование тела запроса без captcha_code", () -> PostRequestFeedbackModel.builder()
                .message(TestData.getMessage())
                .phone(TestData.getPhoneNubmer())
                .captcha("123")
                .cardNumber(TestData.getCardNubmer())
                .email(TestData.getEmail())
                .name(TestData.getName())
                .surname(TestData.getSurname())
                .captchaCode("0f4437ce75d6cee0a0117684268e20f2")
                .isLegalEntity(false)
                .files(List.of())
                .themeId(75661)
                .build());
        PostErrorResponseFeedbackModel response = step("Отправка POST запроса без captcha code в header", () -> given()
                .spec(RequestSpec.baseSpec()))
                .body(requestBody)
                .when()
                .post(Endpoints.FEEDBACK)
                .then()
                .spec(ResponseSpec.errorResponse(400))
                .extract().body().as(PostErrorResponseFeedbackModel.class);
        PostErrorResponseFeedbackModel.ErrorData errorData = response.getData().get(0);

        assertEquals("INVALID_CAPTCHA", errorData.getError_code());
        assertEquals("captcha", errorData.getField());
        assertEquals("Invalid captcha.", errorData.getMessage());

    }
}
