package org.sofka.challenge.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "country")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String code;
}
