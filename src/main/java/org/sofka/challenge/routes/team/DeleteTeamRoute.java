package org.sofka.challenge.routes.team;

import org.sofka.challenge.usecases.team.DeleteTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteTeamRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase useCase) {
        return route(DELETE("/api/v1/team/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(voidResponse -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
