package nl.coinance.cryptocurrency.service;

import nl.coinance.cryptocurrency.bitvavo.BitvavoClient;
import nl.coinance.cryptocurrency.bitvavo.dto.BitvavoMarketTradeDTO;
import nl.coinance.cryptocurrency.service.exception.NotFoundException;
import nl.coinance.cryptocurrency.web.dto.MarketTradeDTO;
import nl.coinance.cryptocurrency.web.mapper.TradeMapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneOffset;

@ApplicationScoped
public class TradeService {

    private final BitvavoClient bitvavoClient;
    private final TradeMapper tradeMapper;

    @Inject
    public TradeService(
            @RestClient final BitvavoClient bitvavoClient,
            final TradeMapper tradeMapper
    ) {
        this.bitvavoClient = bitvavoClient;
        this.tradeMapper = tradeMapper;
    }

    public MarketTradeDTO getTrades(final String market, final LocalDate endDate) {
        final long endEpochMilli = endDate.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        return bitvavoClient.getTrades(market, null, endEpochMilli, null).stream()
                .map(tradeMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(BitvavoMarketTradeDTO.class));
    }

}
