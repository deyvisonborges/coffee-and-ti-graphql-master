package br.com.coffeeandit.graphql.repository;

import br.com.coffeeandit.graphql.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByName(final String name);

    List<Book> findByNameIgnoreCaseContaining(final String name);

}
