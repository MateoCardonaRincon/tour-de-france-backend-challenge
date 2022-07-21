package org.sofka.challenge.routes.team;

import org.sofka.challenge.usecases.team.GetTeamByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetTeamByIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getTeamById(GetTeamByIdUseCase useCase) {
        return route(GET("/api/v1/team/get/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(teamDTO -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(teamDTO))
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
