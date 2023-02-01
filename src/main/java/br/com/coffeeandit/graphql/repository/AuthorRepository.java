package br.com.coffeeandit.graphql.repository;

import br.com.coffeeandit.graphql.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
