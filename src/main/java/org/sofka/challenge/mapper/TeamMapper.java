package org.sofka.challenge.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sofka.challenge.collections.Team;
import org.sofka.challenge.dto.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class TeamMapper {

    private final ModelMapper modelMapper;

    public Function<Team, TeamDTO> convertTeamToDTO() {
        return (team -> modelMapper.map(team, TeamDTO.class));
    }

    public Function<TeamDTO, Team> convertTeamDTOToEntity() {
        return (teamDTO -> modelMapper.map(teamDTO, Team.class));
    }
}
