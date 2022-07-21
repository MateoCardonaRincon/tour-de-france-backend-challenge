package org.sofka.challenge.routes.country;

import org.sofka.challenge.dto.CountryDTO;
import org.sofka.challenge.usecases.country.UpdateCountryUseCase;
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
public class UpdateCountryRoute {

    @Bean
    public RouterFunction<ServerResponse> updateCountry(UpdateCountryUseCase useCase) {

        Function<CountryDTO, Mono<ServerResponse>> executor = countryDTO -> useCase.apply(countryDTO)
                .flatMap(country -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(country));

        return route(PUT("/api/v1/country/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CountryDTO.class)
                        .flatMap(executor)
                        .onErrorResume(IllegalStateException.class, error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
