package nl.coinance.cryptocurrency.web.mapper;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "cdi")
public class TimeMapper {

    @EpochMilliToLocalDateTimeMapper
    public LocalDateTime toLocalDateTimeFromEpochMilli(Long timestamp) {
        return toLocalDateTimeFromEpochMilli(timestamp, ZoneId.systemDefault());
    }

    @EpochMilliToLocalDateTimeMapper
    public LocalDateTime toLocalDateTimeFromEpochMilli(Long timestamp, ZoneId zoneId) {
        return Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDateTime();
    }


}
