package nl.coinance.cryptocurrency.bitvavo.provider;

import nl.coinance.cryptocurrency.bitvavo.security.BitvavoSigner;
import nl.coinance.cryptocurrency.bitvavo.security.SignatureInfo;
import nl.coinance.cryptocurrency.config.BitvavoConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ApplicationScoped
public class BitvavoSignatureProvider implements ClientRequestFilter, WriterInterceptor {

    private static final String KEY_HEADER = "Bitvavo-Access-Key";
    private static final String SIGNATURE_HEADER = "Bitvavo-Access-Signature";
    private static final String TIMESTAMP_HEADER = "Bitvavo-Access-Timestamp";
    private static final String WINDOW_HEADER = "Bitvavo-Access-Window";

    private static final String ENDPOINT_PROPERTY = "BitvavoSignatureFilter.endpoint";
    private static final String METHOD_PROPERTY = "BitvavoSignatureFilter.method";

    private final BitvavoConfig bitvavoConfig;
    private final BitvavoSigner bitvavoSigner;

    @Inject
    public BitvavoSignatureProvider(BitvavoConfig bitvavoConfig, BitvavoSigner bitvavoSigner) {
        this.bitvavoConfig = bitvavoConfig;
        this.bitvavoSigner = bitvavoSigner;
    }

    @Override
    public void filter(ClientRequestContext context) {
        context.setProperty(ENDPOINT_PROPERTY, getRequestUri(context.getUri()));
        context.setProperty(METHOD_PROPERTY, context.getMethod());

        if (Objects.isNull(context.getEntity())) {
            final SignatureInfo signatureInfo = SignatureInfo.builder()
                    .requestMethod(String.valueOf(context.getProperty(METHOD_PROPERTY)))
                    .requestUri(String.valueOf(context.getProperty(ENDPOINT_PROPERTY)))
                    .timestamp(System.currentTimeMillis())
                    .build();

            context.getHeaders().putSingle(SIGNATURE_HEADER, bitvavoSigner.sign(signatureInfo));
            context.getHeaders().putSingle(TIMESTAMP_HEADER, signatureInfo.getTimestamp());
        }

        context.getHeaders().putSingle(KEY_HEADER, bitvavoConfig.getApiKey());
        context.getHeaders().putSingle(WINDOW_HEADER, bitvavoConfig.getAccessWindow());
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        final LoggingStream payloadStream = new LoggingStream(context.getOutputStream());

        context.setOutputStream(payloadStream);
        context.proceed();

        final SignatureInfo signatureInfo = SignatureInfo.builder()
                .requestMethod(String.valueOf(context.getProperty(METHOD_PROPERTY)))
                .requestUri(String.valueOf(context.getProperty(ENDPOINT_PROPERTY)))
                .timestamp(System.currentTimeMillis())
                .payload(payloadStream.readAll())
                .build();

        context.getHeaders().putSingle(SIGNATURE_HEADER, bitvavoSigner.sign(signatureInfo));
        context.getHeaders().putSingle(TIMESTAMP_HEADER, signatureInfo.getTimestamp());
    }

    private static String getRequestUri(final URI uri) {
        final StringBuilder builder = new StringBuilder();
        builder.append(uri.getPath());

        if (Objects.nonNull(uri.getQuery()) && !uri.getQuery().isEmpty()) {
            builder.append("?");
            builder.append(uri.getQuery());
        }

        return builder.toString();
    }

    private static class LoggingStream extends FilterOutputStream {

        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        LoggingStream(final OutputStream out) {
            super(out);
        }

        String readAll() {
            return baos.toString(StandardCharsets.UTF_8);
        }

        @Override
        public void write(final int bytes) throws IOException {
            baos.write(bytes);
            out.write(bytes);
        }

    }
}
