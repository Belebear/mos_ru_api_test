package models;

import lombok.Data;

@Data
public class PostErrorResponseWithObjectCheckIfCardReadyModel {

    private String code;
    private String data;
    private String message;
    private Errors errors;

    @Data
    public static class Errors {
        private String captcha;
    }
}


