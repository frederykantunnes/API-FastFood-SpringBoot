package br.com.atdsistemas.fastfood.model.dto.error_message;

public class ErrorMessageDTO {

    private String error;

    public ErrorMessageDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
