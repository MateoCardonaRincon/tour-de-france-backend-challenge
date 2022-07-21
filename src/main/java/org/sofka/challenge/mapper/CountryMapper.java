package org.sofka.challenge.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sofka.challenge.collections.Country;
import org.sofka.challenge.dto.CountryDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class CountryMapper {

    private final ModelMapper modelMapper;

    public Function<Country, CountryDTO> convertCountryToDTO(){
        return country -> modelMapper.map(country, CountryDTO.class);
    }

    public Function<CountryDTO, Country> convertCountryDTOToEntity(){
        return countryDTO -> modelMapper.map(countryDTO, Country.class);
    }
}
