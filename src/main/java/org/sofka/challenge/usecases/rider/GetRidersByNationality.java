package org.sofka.challenge.usecases.rider;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.mapper.RiderMapper;
import org.sofka.challenge.repository.IRiderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetRidersByNationality implements Function<String, Flux<RiderDTO>> {

    private final IRiderRepository repository;
    private final RiderMapper riderMapper;

    @Override
    public Flux<RiderDTO> apply(String nationality) {
        return repository.findAllByCountryName(nationality)
                .map(rider -> riderMapper.convertRiderToDTO().apply(rider));
    }
}
