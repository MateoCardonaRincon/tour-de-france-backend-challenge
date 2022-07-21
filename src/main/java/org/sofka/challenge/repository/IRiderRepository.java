package org.sofka.challenge.repository;

import org.sofka.challenge.collections.Rider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IRiderRepository extends ReactiveMongoRepository<Rider, String> {

    Flux<Rider> findAllByTeamCode(String teamCode);

    Flux<Rider> findAllByCountryName(String countryName);
}
