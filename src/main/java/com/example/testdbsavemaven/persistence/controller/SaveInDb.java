package com.example.testdbsavemaven.persistence.controller;

import com.example.testdbsavemaven.persistence.model.Author;
import com.example.testdbsavemaven.persistence.model.Book;
import com.example.testdbsavemaven.persistence.repository.AuthorRepository;
import com.example.testdbsavemaven.persistence.repository.BookRepository;
import com.example.testdbsavemaven.persistence.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class SaveInDb {

    private final TestService testService;

    @GetMapping
    void save() {
        testService.save();
    }
}
