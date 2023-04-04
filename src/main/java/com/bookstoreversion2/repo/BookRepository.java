package com.bookstoreversion2.repo;


import com.bookstoreversion2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
