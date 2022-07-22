package org.sofka.challenge.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class RiderDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @NotNull
    @Min(value = 1)
    @Max(value = 999)
    private Short competitionNumber;

    @Valid
    private TeamDTO team;

    @Valid
    private CountryDTO country;
}