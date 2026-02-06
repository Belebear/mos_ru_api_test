package models;

import lombok.Data;

import java.util.List;

@Data
public class PostErrorResponseFeedbackModel {

    private String code;
    private String type;
    private boolean error;
    private List<ErrorData> data;

    @Data
    public static class ErrorData {
        public String error_code;
        public String field;
        public String message;
    }
}
