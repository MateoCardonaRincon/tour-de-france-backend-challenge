package org.sofka.challenge.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sofka.challenge.dto.CountryDTO;
import org.sofka.challenge.dto.TeamDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rider")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rider {

    @Id
    private String id;

    private String fullName;

    @Indexed(unique = true)
    private Short competitionNumber;

    private TeamDTO team;

    private CountryDTO country;
}
