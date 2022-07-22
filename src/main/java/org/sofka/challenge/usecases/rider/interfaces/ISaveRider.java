package org.sofka.challenge.usecases.rider.interfaces;

import org.sofka.challenge.dto.RiderDTO;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
@Validated
public interface ISaveRider {
    Mono<RiderDTO> apply(@Valid RiderDTO riderDTO);
}