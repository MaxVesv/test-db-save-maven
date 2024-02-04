package com.example.testdbsavemaven.persistence.controller;

import com.example.testdbsavemaven.persistence.model.Author;
import com.example.testdbsavemaven.persistence.model.Book;
import com.example.testdbsavemaven.persistence.repository.AuthorRepository;
import com.example.testdbsavemaven.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class SaveInDb {

    Integer counter = 0;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping
    void save() {
        counter ++;

        Author author = new Author();
        author.setName("NameAu" + counter);
        Author authorAfter = authorRepository.save(author);
        Author authorFromDb = authorRepository.findById(authorAfter.getId()).orElse(null);

        Book book = new Book();
        book.setTitle("Title" + counter);
        book.setAuthor(authorAfter);
        Book bookAfter = bookRepository.save(book);

        authorFromDb.getBooks().add(bookAfter);
        authorRepository.save(authorFromDb);

        Book bookFromDB = bookRepository.findById(bookAfter.getId()).orElse(null);
        Author test = authorRepository.findById(authorAfter.getId()).orElse(null);

    }
}
