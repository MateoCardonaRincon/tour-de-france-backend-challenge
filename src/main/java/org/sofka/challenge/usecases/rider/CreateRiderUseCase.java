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
public class CreateRiderUseCase implements Function<RiderDTO, Mono<RiderDTO>> {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Mono<RiderDTO> apply(RiderDTO riderDTO) {
        return repository.save(riderMapper.convertRiderDTOToEntity().apply(riderDTO))
                .map(rider -> riderMapper.convertRiderToDTO().apply(rider));
    }
}
