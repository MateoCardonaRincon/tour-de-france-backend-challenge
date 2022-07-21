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
public class UpdateCountryUseCase implements Function<CountryDTO, Mono<CountryDTO>> {

    private final ICountryRepository repository;
    private final CountryMapper countryMapper;

    @Override
    public Mono<CountryDTO> apply(CountryDTO countryDTO) {
        return repository.findById(countryDTO.getId())
                .switchIfEmpty(Mono.error(new IllegalStateException("Specified country does not exist!")))
                .flatMap(country -> repository.save(countryMapper.convertCountryDTOToEntity().apply(countryDTO)))
                .map(updatedCountry -> countryMapper.convertCountryToDTO().apply(updatedCountry));
    }
}
