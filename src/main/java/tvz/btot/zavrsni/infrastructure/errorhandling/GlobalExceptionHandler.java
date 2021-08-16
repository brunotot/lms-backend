package tvz.btot.zavrsni.infrastructure.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "tvz.btot.zavrsni.web.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        int responseStatus = HttpStatus.BAD_REQUEST.value();
        List<ValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> {
                    String objectName = e.getObjectName();
                    String fieldName = e.getField();
                    Object rejectedValue = e.getRejectedValue();
                    String defaultMessage = e.getDefaultMessage();
                    String messageCode = "Validation failed";
                    if (defaultMessage != null && defaultMessage.startsWith("[") && defaultMessage.endsWith("]")) {
                        messageCode = defaultMessage.substring(1, defaultMessage.length() - 1);
                    }
                    return new ValidationError(messageCode, objectName, fieldName, rejectedValue);
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(responseStatus)
                .body(new ValidationErrorResponse(responseStatus, "Validation failed message", errors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Problem> handleResourceNotFoundException(final ResourceNotFoundException e) {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, "Not found error", e.getMessage(), e);
        return handleApiException(apiException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleServerException(final Exception e) {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error message", e.getMessage(), e);
        return handleApiException(apiException);
    }

    @ExceptionHandler(ProblemException.class)
    public ResponseEntity<Problem> handleApiException(final ProblemException e) {
        return buildResponse(e.getProblem());
    }

    private static ResponseEntity<Problem> buildResponse(final Problem problem) {
        return ResponseEntity.status(problem.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(problem);
    }
}