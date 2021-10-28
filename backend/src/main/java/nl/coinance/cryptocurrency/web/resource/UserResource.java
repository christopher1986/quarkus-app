package nl.coinance.cryptocurrency.web.resource;

import nl.coinance.cryptocurrency.service.UserService;
import nl.coinance.cryptocurrency.web.dto.NewUserDTO;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/users")
@RequestScoped
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(final NewUserDTO userDTO) {
        userService.createUser(userDTO);
        return Response.status(Response.Status.CREATED).build();

    }

}
