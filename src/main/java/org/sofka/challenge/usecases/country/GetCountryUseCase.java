package org.sofka.challenge.usecases.country;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.CountryDTO;
import org.sofka.challenge.mapper.CountryMapper;
import org.sofka.challenge.repository.ICountryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetCountryUseCase implements Supplier<Flux<CountryDTO>> {

    private final ICountryRepository repository;
    private final CountryMapper countryMapper;

    @Override
    public Flux<CountryDTO> get() {
        return repository.findAll().map(country -> countryMapper.convertCountryToDTO().apply(country));
    }
}
