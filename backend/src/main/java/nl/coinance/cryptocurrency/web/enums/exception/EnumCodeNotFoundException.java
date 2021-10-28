package nl.coinance.cryptocurrency.web.enums.exception;

public class EnumCodeNotFoundException extends RuntimeException {

    public EnumCodeNotFoundException(final Class<?> classType, String enumCode) {
        super(String.format("%s: No enum value found for %s", classType.getName(), enumCode));
    }
}
