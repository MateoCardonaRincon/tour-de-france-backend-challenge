package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.mapper.RiderMapper;
import org.sofka.challenge.repository.IRiderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.naming.SizeLimitExceededException;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class CreateRiderUseCase implements Function<RiderDTO, Mono<RiderDTO>> {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Mono<RiderDTO> apply(RiderDTO riderDTO) {
        return repository.findAllByTeamCode(riderDTO.getTeam().getCode()).collectList()
                .flatMap(riders ->
                        riders.size() < 8 ? repository.save(riderMapper.convertRiderDTOToEntity().apply(riderDTO))
                                .onErrorResume(error -> Mono.error(
                                        new IllegalStateException("Something went wrong while creating the rider. Competition number mus be unique."))) :
                                Mono.error(new SizeLimitExceededException("This team had reached the maximum amount of riders (8)")))
                .map(rider -> riderMapper.convertRiderToDTO().apply(rider));
    }
}
