package org.sofka.challenge.routes.team;

import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.usecases.team.CreateTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateTeamRoute {

    @Bean
    public RouterFunction<ServerResponse> createTeam(CreateTeamUseCase useCase) {

        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> useCase.apply(teamDTO)
                .flatMap(team -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(team));

        return route(POST("/api/v1/team/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(executor)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
