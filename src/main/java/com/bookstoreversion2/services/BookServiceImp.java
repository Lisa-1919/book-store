package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.Discount;
import com.bookstoreversion2.data.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DiscountServiceImp discountServiceImp;

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
        book.getImages().forEach(bookImage -> {bookImage.setBook(book);});
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }

    @Override
    public void updateBook(Book updateBook) {
        bookRepository.save(updateBook);
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

    @Override
    public List<Book> genreFilter(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> priceFilter(double min, double max) {
        if(min > max){
            double temp = min;
            min = max;
            max = temp;
        }
        List<Book> books = new ArrayList<>();
        for(Book book : bookRepository.findAll()){
            if(book.getPrice() > min && book.getPrice() < max){
                books.add(book);
            }
        }
        return books;
    }

    public boolean is(Book book){
        List<Discount> discounts = discountServiceImp.getAllDiscounts();
        for(Discount discount: discounts){
            String value = discount.getValue();
            if(book.getAuthor().equals(value) || book.getPublisher().equals(value) || book.getGenre().equals(value)) {
                book.setPrice(book.getPrice()*(1-discount.getDiscountAmount()));
                return true;
            }
            else return false;
        }
        return true;
    }
}
