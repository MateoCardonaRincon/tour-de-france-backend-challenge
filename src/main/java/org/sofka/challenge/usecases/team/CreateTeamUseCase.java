package org.sofka.challenge.usecases.team;

import lombok.AllArgsConstructor;
import org.sofka.challenge.dto.TeamDTO;
import org.sofka.challenge.mapper.TeamMapper;
import org.sofka.challenge.repository.ITeamRepository;
import org.sofka.challenge.usecases.team.interfaces.ISaveTeam;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CreateTeamUseCase implements ISaveTeam {

    private final ITeamRepository repository;
    private final TeamMapper teamMapper;

    @Override
    public Mono<TeamDTO> apply(TeamDTO teamDTO) {
        return repository.save(teamMapper.convertTeamDTOToEntity().apply(teamDTO))
                .map(team -> teamMapper.convertTeamToDTO().apply(team));
    }
}
