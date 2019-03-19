package py.com.mojeda.service.web.ws;

import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import py.com.mojeda.service.web.exception.ValidationErrorResponse;

/**
 * Class to handle Exceptions and return ResponseEntity
 *
 * @author mojeda
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Get the error messages for invalid fields
        String errorMessage;
        errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        // Create ValidationErrorResponse object using error messages and request details
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(errorMessage, request.getDescription(false));
        return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
