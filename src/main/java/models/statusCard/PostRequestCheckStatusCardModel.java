package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestCheckStatusCardModel {
    private String captcha;
    private String card_number;
    private String card_series;
    private String number_dul;
}
