package org.sofka.challenge.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Document(collection = "rider")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rider {

    @Id
    private String id;

    @Size(min = 3, max = 50)
    private String fullName;

    @Indexed(unique = true)
    @Min(value = 1)
    @Max(value = 999)
    private Short competitionNumber;

    private Team team;

    private Country country;
}
