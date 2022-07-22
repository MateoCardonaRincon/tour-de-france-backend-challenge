package org.sofka.challenge.usecases.country.interfaces;

import org.sofka.challenge.dto.CountryDTO;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
@Validated
public interface ISaveCountry {

    Mono<CountryDTO> apply(@Valid CountryDTO countryDTO);
}
