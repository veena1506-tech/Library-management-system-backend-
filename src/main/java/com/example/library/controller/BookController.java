package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "${app.cors.allowed-origin}")
public class BookController {
    private final BookRepository repository;
    public BookController(BookRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Book> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Book one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        validate(book);
        book.setId(null);
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book incoming) {
        Book book = repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        validate(incoming);
        book.setTitle(incoming.getTitle());
        book.setAuthor(incoming.getAuthor());
        book.setIsbn(incoming.getIsbn());
        book.setCategory(incoming.getCategory());
        book.setQuantity(incoming.getQuantity());
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        repository.deleteById(id);
    }

    private void validate(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()
            || book.getAuthor() == null || book.getAuthor().isBlank()
            || book.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Title and author are required; quantity cannot be negative");
        }
    }
}
