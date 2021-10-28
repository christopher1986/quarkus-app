package nl.coinance.cryptocurrency.web.resource;

import io.smallrye.jwt.build.Jwt;
import nl.coinance.cryptocurrency.model.User;
import nl.coinance.cryptocurrency.service.AuthService;
import nl.coinance.cryptocurrency.web.dto.CredentialsDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashSet;

@Path("api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final String issuer;

    private final AuthService authService;

    @Inject
    public AuthResource(
            final AuthService authService,
            @ConfigProperty(name = "mp.jwt.verify.issuer") final String issuer
    ) {
        this.authService = authService;
        this.issuer = issuer;
    }

    @POST
    @Path("/login")
    public String login(final CredentialsDTO credentialsDTO) {
        final User user = authService.authenticate(credentialsDTO);
        return Jwt.issuer(issuer)
                .upn(user.getUsername())
                .groups(user.getRoleNames())
                .claim(Claims.given_name.name(), user.getFirstName())
                .claim(Claims.family_name.name(), user.getLastName())
                .sign();
    }

//    @GET
//    @Path("/login")
//    public String login() {
//        return Jwt.issuer(issuer)
//                .upn("jdoe@quarkus.io")
//                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
//                .claim(Claims.birthdate.name(), "2001-07-13")
//                .sign();
//    }

}
