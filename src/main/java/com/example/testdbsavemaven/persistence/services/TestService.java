package com.example.testdbsavemaven.persistence.services;


import com.example.testdbsavemaven.persistence.model.Author;
import com.example.testdbsavemaven.persistence.model.Book;
import com.example.testdbsavemaven.persistence.repository.AuthorRepository;
import com.example.testdbsavemaven.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TestService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    Integer counter = 0;

//    @Transactional
    public void save() {

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

        if (setAuthor == null) {
            Set<Book> newList = new HashSet<>();
            newList.add(bookAfter);
            authorFromDb2.setBooks(newList);
        }

//        //в полученный спиок добавляем созданный зависимый объект и ловим nullPointer потому что вместо set у нас null
//        setAuthor.add(bookAfter);
//
//        authorFromDb2.setBooks(setAuthor);

        authorRepository.save(authorFromDb2);

        Book bookFromDB = bookRepository.findById(bookAfter.getId()).orElse(null);
        Author test = authorRepository.findById(authorAfter.getId()).orElse(null);

    }

}
