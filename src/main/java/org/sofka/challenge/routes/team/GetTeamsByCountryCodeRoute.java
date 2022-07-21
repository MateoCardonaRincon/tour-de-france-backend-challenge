package org.sofka.challenge.routes.team;

import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.usecases.team.GetTeamsByCountryCodeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetTeamsByCountryCodeRoute {

    @Bean
    public RouterFunction<ServerResponse> getTeamsByCountryCode(GetTeamsByCountryCodeUseCase useCase) {

        return route(GET("/api/v1/team/get-by-country-code/{countryCode}"),
                request ->
                        ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("countryCode")), TeamDTO.class)));
    }
}