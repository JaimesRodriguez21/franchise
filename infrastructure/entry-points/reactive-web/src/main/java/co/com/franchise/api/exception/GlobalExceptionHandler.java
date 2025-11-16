package co.com.franchise.api.exception;

import co.com.franchise.api.dtos.responses.utils.ErrorResponse;
import co.com.franchise.usecase.exceptions.BusinessException;
import co.com.franchise.usecase.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler implements WebExceptionHandler {

    private static final String VALIDATION_EXCEPTION = "C03";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BusinessException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(ex.getCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(ex.getCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(VALIDATION_EXCEPTION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof IllegalArgumentException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            ErrorResponse error = new ErrorResponse("C03", ex.getMessage());
            DataBuffer buffer = null;
            try {
                buffer = exchange.getResponse()
                        .bufferFactory()
                        .wrap(new ObjectMapper().writeValueAsBytes(error));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }
        return Mono.error(ex);
    }
}
