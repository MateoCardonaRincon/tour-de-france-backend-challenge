package org.sofka.challenge.routes.rider;

import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.usecases.rider.GetAllRidersUseCase;
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
public class GetAllRidersRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllRiders(GetAllRidersUseCase useCase){
        return route(GET("/api/v1/rider/get-all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), RiderDTO.class)));
    }
}
