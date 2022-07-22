package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.mapper.RiderMapper;
import org.sofka.challenge.repository.IRiderRepository;
import org.sofka.challenge.usecases.rider.interfaces.ISaveRider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UpdateRiderUseCase implements ISaveRider {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Mono<RiderDTO> apply(RiderDTO riderDTO) {
        return repository.findById(riderDTO.getId())
                .switchIfEmpty(Mono.error(new IllegalStateException("Specified rider does not exist!")))
                .flatMap(rider -> repository.save(riderMapper.convertRiderDTOToEntity().apply(riderDTO)))
                .map(updatedRider -> riderMapper.convertRiderToDTO().apply(updatedRider));
    }
}
