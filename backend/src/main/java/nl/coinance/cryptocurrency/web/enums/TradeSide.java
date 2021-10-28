package nl.coinance.cryptocurrency.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TradeSide implements CodeEnum {

    BUY("buy", "Indicates trade is a buy order"),
    SELL("sell", "Indicates trade is a sell order");

    private final String code;
    private final String description;

}
