package nl.coinance.cryptocurrency.web.mapper;

import nl.coinance.cryptocurrency.bitvavo.dto.BitvavoMarketTradeDTO;
import nl.coinance.cryptocurrency.web.dto.MarketTradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = { EnumMapper.class, TimeMapper.class })
public interface TradeMapper {

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "timestamp", target = "datetime", qualifiedBy = EpochMilliToLocalDateTimeMapper.class)
    @Mapping(source = "side", target = "side")
    MarketTradeDTO toDTO(final BitvavoMarketTradeDTO tradeDTO);

}
