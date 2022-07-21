package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.mapper.RiderMapper;
import org.sofka.challenge.repository.IRiderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetRiderByIdUseCase implements Function<String, Mono<RiderDTO>> {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Mono<RiderDTO> apply(String riderId) {
        return repository.findById(riderId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Rider with id " + riderId + " not found.")))
                .map(rider -> riderMapper.convertRiderToDTO().apply(rider));
    }
}
