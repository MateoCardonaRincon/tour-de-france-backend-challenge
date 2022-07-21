package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.repository.IRiderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DeleteRiderUseCase implements Function<String, Mono<Void>> {

    private final IRiderRepository repository;

    @Override
    public Mono<Void> apply(String riderId) {
        return repository.findById(riderId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Specified rider does not exist!")))
                .flatMap(rider -> repository.deleteById(riderId));
    }
}
