package org.sofka.challenge.usecases.team.interfaces;

import org.sofka.challenge.dto.TeamDTO;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
@Validated
public interface ISaveTeam {

    Mono<TeamDTO> apply(@Valid TeamDTO teamDTO);
}

