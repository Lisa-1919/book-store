package com.bookstoreversion2.services;


import com.bookstoreversion2.entities.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addNewBook(Book book);
    void deleteBook(Book book);

    void updateBook(Book updateBook);

    List<Book> search(String quest);

}
