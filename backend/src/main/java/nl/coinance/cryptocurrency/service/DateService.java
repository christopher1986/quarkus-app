package nl.coinance.cryptocurrency.service;

import nl.coinance.cryptocurrency.config.AppConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.toIntExact;

@ApplicationScoped
public class DateService {

    private final AppConfig appConfig;

    @Inject
    public DateService(final AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public Collection<LocalDate> getLocalDates() {
        final LocalDate startDate = createLocalDate(appConfig.getYear());
        final LocalDate endDate = createLocalDate(Year.now().getValue());
        final long numberOfYears = ChronoUnit.YEARS.between(startDate, endDate);

        return IntStream.range(0, toIntExact(numberOfYears))
                .mapToObj(startDate::plusYears)
                .collect(Collectors.toList());
    }

    private LocalDate createLocalDate(final int year) {
        return LocalDate.ofYearDay(year, 1);
    }

}
