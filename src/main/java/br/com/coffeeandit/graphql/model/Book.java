package br.com.coffeeandit.graphql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    private String id;
    private String name;
    private int pageCount;
    private String isbn;
    private String authorId;
    private BigDecimal price;

}