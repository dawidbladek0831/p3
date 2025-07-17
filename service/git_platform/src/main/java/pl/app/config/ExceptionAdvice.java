package pl.app.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.app.common.exception.*;


@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> authenticationExceptionHandler(AuthenticationException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(apiError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ApiError> authorizationExceptionHandler(AuthorizationException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(apiError);
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ApiError> invalidStateExceptionHandler(InvalidStateException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiError> iOExceptionHandler(IOException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundExceptionHandler(NotFoundException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> validationExceptionHandler(Exception exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> exceptionHandler(Exception exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }
}
