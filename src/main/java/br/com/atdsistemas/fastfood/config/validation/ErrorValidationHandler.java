package br.com.atdsistemas.fastfood.config.validation;

import br.com.atdsistemas.fastfood.model.dto.error_message.ErrorMessageDTO;
import br.com.atdsistemas.fastfood.model.dto.error_message.ErrorMessageFieldsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorValidationHandler {
    @Autowired
    MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorMessageFieldsDTO> handle(MethodArgumentNotValidException exception){
        List<ErrorMessageFieldsDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            dto.add(new ErrorMessageFieldsDTO(e.getField(), message));
            dto.add(new ErrorMessageFieldsDTO(e.getField(), message));
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ErrorMessageDTO handle(UnexpectedTypeException exception){
        String message = exception.getMessage();
        return new ErrorMessageDTO(message);
    }
}
