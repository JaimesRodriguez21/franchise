package co.com.franchise.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IllegalArgumentException extends RuntimeException {
    private final String message;

    public IllegalArgumentException(String message) {
        this.message = message;
    }
}


