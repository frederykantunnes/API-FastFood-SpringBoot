package br.com.atdsistemas.fastfood.model.dto.error_message;

public class ErrorMessageFieldsDTO {

    private String field;
    private String error;

    public ErrorMessageFieldsDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
