package org.sofka.challenge.repository;

import org.sofka.challenge.collections.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends ReactiveMongoRepository<Country, String> {
}
