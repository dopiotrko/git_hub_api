package GitHubRestapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

record ApiErrorResponse(int code, String message) { }

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<ApiErrorResponse> handleHttpClientErrorException(
            HttpClientErrorException ex, HttpServletRequest httpServletRequest) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getStatusCode().value(), ex.getStatusText());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }

}