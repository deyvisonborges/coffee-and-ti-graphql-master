package br.com.coffeeandit.graphql.domain;

import br.com.coffeeandit.graphql.model.Author;
import br.com.coffeeandit.graphql.model.Book;
import br.com.coffeeandit.graphql.model.Info;
import br.com.coffeeandit.graphql.repository.AuthorRepository;
import br.com.coffeeandit.graphql.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BookService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookInfoService bookInfoService;


    public Optional<Book> getById(final String id) {

        return bookRepository.findById(id);
    }

    public Optional<Book> getByName(final String name) {

        return bookRepository.findByName(name);
    }
    public List<Book> findyByName(final String name) {

        return bookRepository.findByNameIgnoreCaseContaining(name);
    }
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    public Info findBookInfo(final Book book) {

        try {
            return bookInfoService.findBookInfo(book).orElse(new Info());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return new Info();
    }


    public Optional<Author> getAuthorById(final String id) {
        return authorRepository.findById(id);
    }


}