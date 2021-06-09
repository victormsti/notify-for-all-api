package com.notifyforall.api.config.exceptions.handler;

import com.notifyforall.api.config.exceptions.RestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * para resetar as mensagens de outros erros
 * deve herdar de: ResponseEntityExceptionHandler
 */
@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorMessage> handleRestException(Exception ex,
                                                            WebRequest request) {
        RestException restException = (RestException) ex;
        ErrorMessage error = new ErrorMessage(restException);

            int status = restException.getStatus().value();


            error = new ErrorMessage(status,
                        restException.getMessage(),
                        restException.getError());

        return new ResponseEntity<>(error, restException.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorMessage> handleIntegrityViolation(Exception ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Unprocessable Entity");
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                          WebRequest request) {
        ValidationMessage validationMessage = new ValidationMessage(ex);
        return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
    }

    @Data
    @NoArgsConstructor
    private static class ErrorMessage {
        private int status;
        private String message;
        private String error;

        public ErrorMessage(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public ErrorMessage(int status, String message, String error) {
            this.status = status;
            this.message = message;
            this.error = error;
        }

        ErrorMessage(RestException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    private static class ValidationMessage extends ErrorMessage {
        private List<FieldMessage> fields;

        ValidationMessage(MethodArgumentNotValidException ex) {
            this.setStatus(HttpStatus.BAD_REQUEST.value());
            this.setMessage("VALIDATION_ERROR");

            this.fields = new ArrayList<>();
            for (FieldError error : ex.getBindingResult().getFieldErrors()) {

                addField(error.getField(), error.getDefaultMessage(), error.getObjectName());
            }
        }

        private void addField(String field, String message, String objectName) {
            FieldMessage fieldMessage = new FieldMessage(field, message, objectName);
            this.fields.add(fieldMessage);
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class FieldMessage {
        private String field;
        private String message;
        private String objectName;
    }

}
