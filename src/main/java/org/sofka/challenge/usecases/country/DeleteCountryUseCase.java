package org.sofka.challenge.usecases.country;

import lombok.AllArgsConstructor;
import org.sofka.challenge.repository.ICountryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DeleteCountryUseCase implements Function<String, Mono<Void>> {

    private final ICountryRepository repository;

    @Override
    public Mono<Void> apply(String countryId) {
        return repository.findById(countryId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Specified country does not exist!")))
                .flatMap(country -> repository.deleteById(countryId));
    }
}
