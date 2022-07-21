package org.sofka.challenge.usecases.country;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.CountryDTO;
import org.sofka.challenge.mapper.CountryMapper;
import org.sofka.challenge.repository.ICountryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetCountryByIdUseCase implements Function<String, Mono<CountryDTO>> {

    private final ICountryRepository repository;
    private final CountryMapper countryMapper;

    @Override
    public Mono<CountryDTO> apply(String countryId) {
        return repository.findById(countryId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Country with id " + countryId + " not found.")))
                .map(country -> countryMapper.convertCountryToDTO().apply(country));
    }
}
