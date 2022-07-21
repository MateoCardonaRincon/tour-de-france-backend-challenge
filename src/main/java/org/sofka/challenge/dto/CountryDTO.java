package org.sofka.challenge.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CountryDTO {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
