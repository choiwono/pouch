package my.examples.pouch.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private String message;
    private boolean check;
    private T response;
}
