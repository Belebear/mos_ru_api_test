package models.statusCard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.readyCard.PostErrorResponseWithObjectCheckIfCardReadyModel;

@Data
public class PostErrorResponseCheckStatusCardModel {
    private String code;
    private String data;
    private String message;
    private PostErrorResponseCheckStatusCardModel.Errors errors;

    @Data
    public static class Errors {
        @JsonProperty("card_series")
        private String cardSeries;

        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("number_d_u_l")
        private String numberDUL;

        private String captcha;
    }
}
