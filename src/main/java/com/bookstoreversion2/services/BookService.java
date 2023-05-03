package com.bookstoreversion2.services;


import com.bookstoreversion2.data.entities.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addNewBook(Book book);
    void deleteBook(Long id);

    void updateBook(Book updateBook);

    List<Book> search(String quest);

    List<Book> sort(String sort);
    List<Book> genreFilter(String genre);
    List<Book> priceFilter(double min, double max);

}
