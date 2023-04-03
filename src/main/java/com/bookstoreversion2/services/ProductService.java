package com.bookstoreversion2.services;


import com.bookstoreversion2.entities.Book;

import java.util.List;

public interface ProductService {

    Book getProductById(Long id);
    List<Book> getAllProducts();
    void addNewProduct(Book book);
    void deleteProduct(Book book);

    void updateProduct(Book updateBook);

    List<Book> search(String quest);

}
