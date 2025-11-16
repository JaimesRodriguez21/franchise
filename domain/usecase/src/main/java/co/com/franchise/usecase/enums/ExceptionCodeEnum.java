package co.com.franchise.usecase.enums;

import lombok.Getter;

@Getter
public enum ExceptionCodeEnum {

    // franchises
    C01FRCH01("C01FRCH01", "Franchise already exists"),
    C01FRCH02("C01FRCH02", "Franchise not found"),
    C01FRCH03("C01FRCH03", "Franchise name already exists"),

    // stores
    C01STOR01("C01STOR01", "Store already exists"),
    C01STOR02("C01STOR02", "Store not found"),
    C01STOR03("C01STOR03", "Store name already exists"),

    // products
    C01PDTS01("C01PDTS01", "Product already exists"),
    C01PDTS02("C01PDTS02", "Product not found"),
    C01PDTS03("C01PDTS03", "Product name already exists"),
    ;


    private final String code;
    private final String message;

    ExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
