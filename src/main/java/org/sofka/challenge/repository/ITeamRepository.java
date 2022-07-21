package org.sofka.challenge.repository;

import org.sofka.challenge.collections.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITeamRepository extends ReactiveMongoRepository<Team, String> {

    Flux<Team> findAllByCountryCode(String countryName);
}
