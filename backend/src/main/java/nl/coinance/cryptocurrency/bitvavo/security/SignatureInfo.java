package nl.coinance.cryptocurrency.bitvavo.security;

import lombok.Builder;
import lombok.Getter;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Supplier;

@Builder
@Getter
public class SignatureInfo {

    private static final String EMPTY_STRING = "";

    private final String requestMethod;
    private final String requestUri;
    private final String payload;
    private final Long timestamp;

    public byte[] getBytes(final Charset charset) {
        return toString().getBytes(charset);
    }

    @Override
    public String toString() {
        return (toStringValue(this::getTimestamp) +
                toStringValue(this::getRequestMethod) +
                toStringValue(this::getRequestUri) +
                toStringValue(this::getPayload));
    }

    private static <T> String toStringValue(Supplier<T> supplier) {
        return Optional.of(supplier)
                .map(Supplier::get)
                .map(String::valueOf)
                .orElse(EMPTY_STRING);
    }
}
