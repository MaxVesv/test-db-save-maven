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

        // создаем главную сущность в БД
        Author author = new Author();
        author.setName("NameAu" + counter);
        Author authorAfter = authorRepository.save(author);
        //перепроверим запись в БД
        Author authorFromDb = authorRepository.findById(authorAfter.getId()).orElse(null);

        //создаем зависимую сущность для списка главной сущности
        Book book = new Book();
        book.setTitle("Title" + counter);
        //устанавливаем связь с главной сущностью
        book.setAuthor(authorAfter);
        //сохраняем в БД
        Book bookAfter = bookRepository.save(book);

        //перепроверим что данные в главной сущности не изменились
        Author authorFromDb2 = authorRepository.findById(authorAfter.getId()).orElse(null);

        // вытаскиваем существующий список из главного объекта (на сулчай если он не подкгрузился)
        // почему мы получаем null а не пустой set???? , а в другом вариенте был не null но потом после выгрузки Set не выгружается потому что связь не работает
        Set<Book> setAuthor = authorFromDb2.getBooks();

        //в полученный спиок добавляем созданный зависимый объект и ловим nullPointer потому что вместо set у нас null
        setAuthor.add(bookAfter);

        authorFromDb2.setBooks(setAuthor);

        authorRepository.save(authorFromDb2);

        Book bookFromDB = bookRepository.findById(bookAfter.getId()).orElse(null);
        Author test = authorRepository.findById(authorAfter.getId()).orElse(null);

    }
}
