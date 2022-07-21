package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.mapper.RiderMapper;
import org.sofka.challenge.repository.IRiderRepository;
import org.sofka.challenge.routes.rider.GetAllRidersRoute;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllRidersUseCase implements Supplier<Flux<RiderDTO>> {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Flux<RiderDTO> get() {
        return repository.findAll()
                .map(rider -> riderMapper.convertRiderToDTO().apply(rider));
    }
}
