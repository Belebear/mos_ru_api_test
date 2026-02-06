package models.statusCard;

import lombok.Data;

import java.util.List;

@Data
public class PostErrorResponseCheckStatusCardWithoutObjectModel {
    private String code;
    private String data;
    private String message;
    private List<String> errors;
}
