package org.sofka.challenge.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sofka.challenge.collections.Rider;
import org.sofka.challenge.dto.RiderDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class RiderMapper {

    private final ModelMapper modelMapper;

    public Function<Rider, RiderDTO> convertRiderToDTO() {
        return (rider -> modelMapper.map(rider, RiderDTO.class));
    }

    public Function<RiderDTO, Rider> convertRiderDTOToEntity() {
        return (riderDTO -> modelMapper.map(riderDTO, Rider.class));
    }
}
