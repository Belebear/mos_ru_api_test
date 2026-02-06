package models.statusCard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestCheckStatusCardModel {
    private String captcha;
    private String card_number;
    private String card_series;

    @JsonProperty("number_dul")
    private String numberDul;
}
