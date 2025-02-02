package com.example.igorgabriel.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * // @ControllerAdvice is a specialization of @Component, allowing to define @ExceptionHandler, @InitBinder, and @ModelAttribute 
 * methods that apply to all @RequestMapping methods.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // @ExceptionHandler is an annotation for handling exceptions in specific handler classes and/or handler methods.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodNotValid(MethodArgumentNotValidException exception) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(message, error.getField());

            dto.add(errorMessageDTO);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
    
}
