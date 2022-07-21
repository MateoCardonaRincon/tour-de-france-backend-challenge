package org.sofka.challenge.routes.rider;

import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.usecases.rider.UpdateRiderUseCase;
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
public class UpdateRiderRoute {

    @Bean
    public RouterFunction<ServerResponse> updateRider(UpdateRiderUseCase useCase) {

        Function<RiderDTO, Mono<ServerResponse>> executor = riderDTO -> useCase.apply(riderDTO)
                .flatMap(rider -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(rider));

        return route(PUT("/api/v1/rider/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RiderDTO.class)
                        .flatMap(executor)
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
