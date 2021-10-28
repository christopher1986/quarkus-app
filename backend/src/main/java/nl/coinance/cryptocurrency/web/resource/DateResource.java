package nl.coinance.cryptocurrency.web.resource;

import nl.coinance.cryptocurrency.service.DateService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/api/dates")
public class DateResource {

    private final DateService dateService;

    @Inject
    public DateResource(final DateService dateService) {
        this.dateService = dateService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getYears() {
        final Collection<Integer> years = dateService.getLocalDates().stream()
                .map(LocalDate::getYear)
                .collect(Collectors.toList());
        return Response.ok(years).build();
    }

}
