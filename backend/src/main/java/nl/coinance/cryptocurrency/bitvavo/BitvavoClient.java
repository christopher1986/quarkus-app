package nl.coinance.cryptocurrency.bitvavo;

import nl.coinance.cryptocurrency.bitvavo.dto.BitvavoMarketTradeDTO;
import nl.coinance.cryptocurrency.bitvavo.provider.BitvavoSignatureProvider;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/v2")
@RegisterRestClient(configKey="bitvavo-api")
@RegisterProvider(BitvavoSignatureProvider.class)
public interface BitvavoClient {

    @GET
    @Path("/{market}/trades")
    @Produces(MediaType.APPLICATION_JSON)
    Collection<BitvavoMarketTradeDTO> getTrades(
            @PathParam("market") final String market,
            @QueryParam("start") final Long start,
            @QueryParam("end") final Long end,
            @QueryParam("limit") @DefaultValue("500") final Integer limit
    );

}
