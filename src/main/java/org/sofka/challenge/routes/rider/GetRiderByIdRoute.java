package org.sofka.challenge.routes.rider;

import org.sofka.challenge.usecases.rider.GetRiderByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetRiderByIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getRiderById(GetRiderByIdUseCase useCase) {
        return route(GET("/api/v1/rider/get/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(riderDTO -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(riderDTO))
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
