package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByGenre(String genre);
}
