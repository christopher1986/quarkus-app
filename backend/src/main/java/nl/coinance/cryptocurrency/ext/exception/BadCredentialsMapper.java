package nl.coinance.cryptocurrency.ext.exception;

import nl.coinance.cryptocurrency.security.exception.BadCredentialsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadCredentialsMapper implements ExceptionMapper<BadCredentialsException> {

    @Override
    public Response toResponse(final BadCredentialsException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
