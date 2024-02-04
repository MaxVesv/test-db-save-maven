package com.example.testdbsavemaven.persistence.repository;

import com.example.testdbsavemaven.persistence.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
