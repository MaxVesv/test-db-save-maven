package com.example.testdbsavemaven.persistence.repository;

import com.example.testdbsavemaven.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
