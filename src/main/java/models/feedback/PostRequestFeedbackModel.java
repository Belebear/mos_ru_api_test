package models.feedback;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostRequestFeedbackModel {
    private String message;
    private String captcha;
    private String cardNumber;
    private boolean isLegalEntity;
    private String email;
    private String phone;
    private String surname;
    private String name;
    private String captchaCode;
    private List<String> files;
    private int themeId;

}
