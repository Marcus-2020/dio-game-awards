package me.dio.gameawards.controller;

import me.dio.gameawards.controller.dto.ApiErrorDTO;
import me.dio.gameawards.service.exception.BusinessException;
import me.dio.gameawards.service.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ApiErrorDTO> handlerNoContentException(NoContentException exception) {
        var error = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDTO> handlerBusinessException(BusinessException exception) {
        var error = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.unprocessableEntity().body(error);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handlerUnexpectedException(Throwable exception) {
        var error = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
