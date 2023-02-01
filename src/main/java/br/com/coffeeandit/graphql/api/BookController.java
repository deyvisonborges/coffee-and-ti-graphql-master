package br.com.coffeeandit.graphql.api;

import br.com.coffeeandit.graphql.exception.NotFoundException;
import br.com.coffeeandit.graphql.model.Info;
import br.com.coffeeandit.graphql.domain.BookService;
import br.com.coffeeandit.graphql.model.Author;
import br.com.coffeeandit.graphql.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @QueryMapping
    public Book bookById(final @Argument String id) {
        return bookService.getById(id).orElseThrow(() -> new NotFoundException("Livro não encontrado"));
    }

    @QueryMapping
    public Book bookByName(final @Argument String name) {

        return bookService.getByName(name).orElseThrow(() -> new NotFoundException("Livro não encontrado"));
    }
    @QueryMapping
    public List<Book> listBooksByName(final @Argument String name) {

        return bookService.findyByName(name);
    }
    @QueryMapping
    public List<Book> books() {

        return bookService.findAll();
    }

    @SchemaMapping(value = "info")
    public Info bookInfo(final Book book) {

        return bookService.findBookInfo(book);

    }

    @SchemaMapping
    public Author author(final Book book) {
        return bookService.getAuthorById(book.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author não encontrado"));
    }
}