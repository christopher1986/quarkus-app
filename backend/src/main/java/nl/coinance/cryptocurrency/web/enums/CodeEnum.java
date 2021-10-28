package nl.coinance.cryptocurrency.web.enums;

import nl.coinance.cryptocurrency.web.enums.exception.EnumCodeNotFoundException;

import java.util.Arrays;

public interface CodeEnum {

    String getCode();
    String getDescription();

    static <T extends CodeEnum> T findByCode(String code, T[] values) {
        return Arrays.stream(values)
                .filter(enumCode -> enumCode.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new EnumCodeNotFoundException(values.getClass().getComponentType(), code));
    }

}
