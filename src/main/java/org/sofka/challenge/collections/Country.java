package org.sofka.challenge.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document(collection = "country")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {

    @Id
    private String id;

    @Size(min = 3, max = 50)
    private String name;

    @Indexed(unique = true)
    @Size(min = 1, max = 3)
    private String code;
}
