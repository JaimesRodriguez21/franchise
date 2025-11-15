package co.com.franchise.usecase.exception;

import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {
    private final String code;
    private final String message;

    public BusinessException(ExceptionCodeEnum exceptionCodeEnum) {
        this.code = exceptionCodeEnum.getCode();
        this.message = exceptionCodeEnum.getMessage();
    }
}