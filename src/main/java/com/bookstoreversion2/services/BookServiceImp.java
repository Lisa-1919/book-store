package com.bookstoreversion2.services;

import com.bookstoreversion2.entities.Book;
import com.bookstoreversion2.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }

    @Override
    public void updateBook(Book updateBook) {
        Book book = bookRepository.findById(updateBook.getId()).get();
        book.setBookType(updateBook.getBookType());
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setEBookURL(updateBook.getEBookURL());
        book.setDescription(updateBook.getDescription());
        book.setGenre(updateBook.getGenre());
     //   book.setImages(updateBook.getImages());
        book.setFreeBookExcerptURL(updateBook.getFreeBookExcerptURL());
        book.setPrice(updateBook.getId());
        book.setPublisher(updateBook.getPublisher());
        book.setStockQuantity(updateBook.getStockQuantity());
        bookRepository.save(book);
    }

    @Override
    public List<Book> search(String quest) {
        List<Book> books = new ArrayList<>();
        for(Book book: bookRepository.findAll()){
            if(book.getTitle().contains(quest) || book.getAuthor().contains(quest) /*|| book.getPublisher().contains(quest) || book.getGenre().contains(quest)*/){
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public List<Book> sort(String sort) {
        List<Book> books = new ArrayList<>();
        if(sort.equals("priceUp")) {
            books = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        }if(sort.equals("priceDown")){
            books = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        }
        if(sort.equals("popular")){
            books = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "salesNumber"));
        }
        return books;
    }

}
