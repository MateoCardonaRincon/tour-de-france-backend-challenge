package org.sofka.challenge.routes.rider;

import org.sofka.challenge.usecases.rider.DeleteRiderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteRiderRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteRider(DeleteRiderUseCase useCase) {
        return route(DELETE("/api/v1/rider/delete/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(voidResponse -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
