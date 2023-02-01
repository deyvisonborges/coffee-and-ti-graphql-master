package br.com.coffeeandit.graphql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    private String id;
    private String firstName;
    private String lastName;
}
