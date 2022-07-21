package org.sofka.challenge.usecases.team;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.mapper.TeamMapper;
import org.sofka.challenge.repository.ITeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetTeamsByCountryCodeUseCase implements Function<String, Flux<TeamDTO>> {

    private final ITeamRepository repository;
    private final TeamMapper teamMapper;

    @Override
    public Flux<TeamDTO> apply(String countryCode) {
        return repository.findAllByCountryCode(countryCode)
                .map(team -> teamMapper.convertTeamToDTO().apply(team));
    }
}
