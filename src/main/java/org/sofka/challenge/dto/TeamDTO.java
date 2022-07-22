package org.sofka.challenge.dto;

import lombok.Data;
import org.sofka.challenge.collections.Country;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TeamDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 1, max = 3)
    private String code;

    @Valid
    private CountryDTO country;
}