package nbe1.team08.gridscircles.common.exception;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import nbe1.team08.gridscircles.common.response.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handleNoSuchElementException(
            NoSuchElementException e
    ) {
        log.info("{} -> {}", e, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Error.of(404, e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(
            IllegalArgumentException e
    ) {
        log.info("{} -> {}", e, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.of(400, e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDeniedException(
            AccessDeniedException e
    ) {
        log.info("{} -> {}", e, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.of(400, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.info("handleNoResourceFoundException", ex);
        return ResponseEntity.status(400)
                .body(Error.of(400, ex.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleAllException(
            Exception e
    ) {
        log.warn("handleAllException", e);
        return ResponseEntity.status(500)
                .body(Error.of(500, "internal server error"));
    }
}
