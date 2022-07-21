package org.sofka.challenge.routes.team;

import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.usecases.team.UpdateTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateTeamRoute {

    @Bean
    public RouterFunction<ServerResponse> updateTeam(UpdateTeamUseCase useCase) {

        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> useCase.apply(teamDTO)
                .flatMap(team -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(team));

        return route(PUT("/api/v1/team/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(executor)
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
