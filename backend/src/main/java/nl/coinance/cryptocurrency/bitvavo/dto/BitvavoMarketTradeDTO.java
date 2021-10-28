package nl.coinance.cryptocurrency.bitvavo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitvavoMarketTradeDTO {

    private BigDecimal amount;
    private BigDecimal price;
    private String side;
    private Long timestamp;

}
