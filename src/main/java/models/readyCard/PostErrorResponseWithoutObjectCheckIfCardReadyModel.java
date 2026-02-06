package models.readyCard;

import lombok.Data;

import java.util.List;

@Data
public class PostErrorResponseWithoutObjectCheckIfCardReadyModel {

    private String code;
    private String data;
    private String message;
    private List<String> errors;
}
