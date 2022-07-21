package org.sofka.challenge.dto;

import lombok.Data;
import org.sofka.challenge.collections.Country;
import org.sofka.challenge.collections.Team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RiderDTO {

    private String id;

    @NotBlank
    private String fullName;

    @NotNull
    private Short competitionNumber;

    @NotBlank
    private Team team;

    @NotBlank
    private Country country;
}