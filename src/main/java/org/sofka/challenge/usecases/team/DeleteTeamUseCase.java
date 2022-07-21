package org.sofka.challenge.usecases.team;

import lombok.AllArgsConstructor;
import org.sofka.challenge.repository.ITeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DeleteTeamUseCase implements Function<String, Mono<Void>> {

    private final ITeamRepository repository;

    @Override
    public Mono<Void> apply(String teamId) {
        return repository.findById(teamId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Specified team does not exist!")))
                .flatMap(team -> repository.deleteById(teamId));
    }
}
