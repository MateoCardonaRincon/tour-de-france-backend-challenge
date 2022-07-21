package org.sofka.challenge.routes.rider;

import org.sofka.challenge.dto.RiderDTO;
import org.sofka.challenge.usecases.rider.GetRidersByNationality;
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
public class GetRiderByNationalityRoute {

    @Bean
    public RouterFunction<ServerResponse> getRiderByNationality(GetRidersByNationality useCase) {
        return route(GET("/api/v1/rider/get-by-nationality/{nationality}"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("nationality")), RiderDTO.class)));
    }
}
