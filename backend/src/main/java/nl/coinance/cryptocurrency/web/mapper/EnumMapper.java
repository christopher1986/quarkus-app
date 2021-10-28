package nl.coinance.cryptocurrency.web.mapper;

import nl.coinance.cryptocurrency.web.dto.EnumDTO;
import nl.coinance.cryptocurrency.web.enums.CodeEnum;
import nl.coinance.cryptocurrency.web.enums.TradeSide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface EnumMapper {

    default EnumDTO toTradeSide(final String code) {
        return toEnumDto(CodeEnum.findByCode(code, TradeSide.values()));
    }

    EnumDTO toEnumDto(CodeEnum codeEnum);

}
