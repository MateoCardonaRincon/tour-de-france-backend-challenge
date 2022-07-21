package org.sofka.challenge.routes.country;

import org.sofka.challenge.usecases.country.GetCountryByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetCountryByIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getCountryById(GetCountryByIdUseCase useCase) {

        return route(GET("/api/v1/country/get/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(countryDTO -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(countryDTO))
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
