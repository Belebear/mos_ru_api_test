package models.readyCard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestCheckIfCardReadyModel {
    private String captcha;
    private String doc_no;
    private String blank_no;

}
