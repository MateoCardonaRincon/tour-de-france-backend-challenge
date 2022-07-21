package org.sofka.challenge.dto;

import lombok.Data;
import org.sofka.challenge.collections.Country;

import javax.validation.constraints.NotBlank;

@Data
public class TeamDTO {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private Country country;
}