package org.sofka.challenge.routes.rider;

import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.usecases.rider.GetRidersByTeamCodeUseCase;
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
public class GetRidersByTeamCodeRoute {

    @Bean
    public RouterFunction<ServerResponse> getRidersByTeamCode(GetRidersByTeamCodeUseCase useCase) {
        return route(GET("/api/v1/rider/get-by-team-code/{teamCode}"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("teamCode")), RiderDTO.class)));
    }
}
