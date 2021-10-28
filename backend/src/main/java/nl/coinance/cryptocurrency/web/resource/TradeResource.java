package nl.coinance.cryptocurrency.web.resource;

import nl.coinance.cryptocurrency.service.TradeService;
import nl.coinance.cryptocurrency.web.dto.MarketTradeDTO;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("api/{market}/trades")
@RequestScoped
public class TradeResource {

    private final TradeService tradeService;

    @Inject
    public TradeResource(final TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrades(
            @PathParam("market") final String market,
            @QueryParam("year") final int year
    ) {
        final MarketTradeDTO tradeDTO = tradeService.getTrades(market, LocalDate.ofYearDay(year, 1));
        return Response.ok(tradeDTO).build();
    }

}
