package nl.coinance.cryptocurrency.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MarketTradeDTO {

    private BigDecimal amount;
    private BigDecimal price;
    private LocalDateTime datetime;
    private EnumDTO side;

}
