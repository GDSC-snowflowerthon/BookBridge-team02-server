package team2.bookbridge.global.config.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import team2.bookbridge.global.common.exception.ApiException;
import team2.bookbridge.global.response.ErrorResponse;

import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleApiException(Exception exception, WebRequest request) {
        if (!Objects.nonNull(exception))
            return null;
        if (exception instanceof ApiException) {
            return handleExceptionInternal(exception, null, new HttpHeaders(), ((ApiException)exception).getStatusCode(),
                    request);
        } else {
            exception.printStackTrace();
            return handleExceptionInternal(exception, null, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest)request;
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(now())
                .status(status.value())
                .message(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                .remote(servletWebRequest.getRequest().getRemoteAddr())
                .path(servletWebRequest.getRequest().getRequestURI())
                .build();
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest)request;
        ErrorResponse response;

        String internalServerErrorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty())
                ? ex.getMessage() : "Internal Server Error";
        if (statusCode.equals(INTERNAL_SERVER_ERROR)) {
            response = ErrorResponse.builder()
                    .timestamp(now())
                    .status(statusCode.value())
                    .message(internalServerErrorMessage)
                    .path(servletWebRequest.getRequest().getRequestURI())
                    .remote(servletWebRequest.getRequest().getRemoteAddr())
                    .build();
        } else {
            response = ErrorResponse.builder()
                    .timestamp(now())
                    .status(statusCode.value())
                    .message(ex.getMessage())
                    .remote(servletWebRequest.getRequest().getRemoteAddr())
                    .path(servletWebRequest.getRequest().getRequestURI())
                    .build();
        }
        return new ResponseEntity<>(response, headers, statusCode);
    }
}
