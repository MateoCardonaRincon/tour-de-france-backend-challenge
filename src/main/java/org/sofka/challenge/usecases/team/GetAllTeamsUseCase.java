package org.sofka.challenge.usecases.team;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.mapper.TeamMapper;
import org.sofka.challenge.repository.ITeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllTeamsUseCase implements Supplier<Flux<TeamDTO>> {

    private final ITeamRepository repository;
    private final TeamMapper teamMapper;

    @Override
    public Flux<TeamDTO> get() {
        return repository.findAll()
                .map(team -> teamMapper.convertTeamToDTO().apply(team));
    }
}
