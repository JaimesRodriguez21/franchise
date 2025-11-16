package co.com.franchise.api.validators;

import co.com.franchise.api.exception.IllegalArgumentException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationUtil {

    private final Validator validator;

    public ValidationUtil(Validator validator) {
        this.validator = validator;
    }

    public <T> Mono<T> validateBody(Mono<T> bodyMono) {
        return bodyMono
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Request body is required")))
                .flatMap(this::validate);
    }

    public <T> Mono<T> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Mono.error(new IllegalArgumentException(errors));
        }
        return Mono.just(object);
    }
}