package org.sofka.challenge.routes.country;

import org.sofka.challenge.dto.CountryDTO;
import org.sofka.challenge.usecases.country.CreateCountryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateCountryRoute {

    @Bean
    public RouterFunction<ServerResponse> createCountry(CreateCountryUseCase useCase) {

        Function<CountryDTO, Mono<ServerResponse>> executor = countryDTO -> useCase.apply(countryDTO)
                .flatMap(country -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(country));

        return route(POST("/api/v1/country/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CountryDTO.class)
                        .flatMap(executor)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
