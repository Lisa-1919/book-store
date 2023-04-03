package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Book getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllProducts() {
        List<Book> bookList = productRepository.findAll();
        return bookList;
    }

    @Override
    public void addNewProduct(Book book) {
        productRepository.save(book);
    }

    @Override
    public void deleteProduct(Book book) {
        productRepository.delete(book);
    }

    @Override
    public void updateProduct(Book updateBook) {
        Book book = productRepository.findById(updateBook.getId()).get();
        book.setBookType(updateBook.getBookType());
        book.setName(updateBook.getName());
        book.setAuthor(updateBook.getAuthor());
        book.setEBookURL(updateBook.getEBookURL());
        book.setDescription(updateBook.getDescription());
        book.setGenre(updateBook.getGenre());
        book.setImages(updateBook.getImages());
        book.setFreeBookExcerptURL(updateBook.getFreeBookExcerptURL());
        book.setPrice(updateBook.getId());
        book.setPublisher(updateBook.getPublisher());
        book.setStockQuantity(updateBook.getStockQuantity());
        productRepository.save(book);
    }

    @Override
    public List<Book> search(String quest) {
        List<Book> books = new ArrayList<>();
        for(Book book: productRepository.findAll()){
            if(book.getName().contains(quest) || book.getAuthor().contains(quest) || book.getPublisher().contains(quest) || book.getGenre().contains(quest)){
                books.add(book);
            }
        }
        return books;
    }

}
