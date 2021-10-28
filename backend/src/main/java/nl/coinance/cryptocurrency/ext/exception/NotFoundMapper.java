package nl.coinance.cryptocurrency.ext.exception;

import nl.coinance.cryptocurrency.service.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(final NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
