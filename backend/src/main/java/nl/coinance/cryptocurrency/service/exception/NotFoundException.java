package nl.coinance.cryptocurrency.service.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final Class<?> classType) {
        super(String.format("No object of type %s was found", classType.getName()));
    }

}
