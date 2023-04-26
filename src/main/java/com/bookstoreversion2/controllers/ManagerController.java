package com.bookstoreversion2.controllers;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.BookImage;
import com.bookstoreversion2.data.entities.Discount;
import com.bookstoreversion2.services.DiscountServiceImp;
import com.bookstoreversion2.services.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Controller
public class ManagerController {


    @Autowired
    private BookServiceImp productServiceImp;

    @Autowired
    private DiscountServiceImp discountServiceImp;

    @Value("D:/book-store/book-images")
    private String uploadImgPath;

    @Value("D:/book-store/book-pdf")
    private String uploadPDFPath;

    public ManagerController() {
    }


    @GetMapping("/manager")
    public String managerPage(Model model){
        return "account_manager";
    }

    @GetMapping("/manager/book/catalog")
    public String catalogPage(Model model){
        List<Book> books = productServiceImp.getAllBooks();
        model.addAttribute("books", books);
        return "catalog_manager";
    }

    @GetMapping("/manager/book/add")
    public String addBookPage(Model model){
        model.addAttribute("book", new Book());
        return "add_book_form";
    }

    @PostMapping("/manager/book/add")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam(value = "_freeBookExcerptURL") MultipartFile file, @RequestParam(value = "_eBookURL") MultipartFile eBook,
                          @RequestParam(value = "_images") ArrayList<MultipartFile> images, Model model) throws IOException {
        if(file != null){
            File uploadFolder = new File(uploadPDFPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String resultPartFileName = UUID.randomUUID() + "." +file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPDFPath + "/" + resultPartFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            book.setFreeBookExcerptURL(resultPartFileName);
        }
        if(eBook != null){
            File uploadFolder = new File(uploadPDFPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String resultEBookFileName = UUID.randomUUID() + "." +eBook.getOriginalFilename();
            try {
                eBook.transferTo(new File(uploadPDFPath + "/" + resultEBookFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            book.setEBookURL(resultEBookFileName);
        }
        book.setImages(new ArrayList<>());
        File uploadFolder = new File(uploadImgPath);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdir();
        }
        images.forEach(image->{
            if(image != null){
                String resultImgName = UUID.randomUUID() + "." +image.getOriginalFilename();
                try {
                    image.transferTo(new File(uploadImgPath + "/" + resultImgName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                book.getImages().add(new BookImage(resultImgName));
            }
        });
        productServiceImp.addNewBook(book);
        return "redirect:/manager/book/catalog";
    }

    @GetMapping("/manager/book/{id}")
    public String bookPage(@PathVariable(name = "id") Long id, Model model){
        Book book = productServiceImp.getBookById(id);
        model.addAttribute("book", book);
        return "book_page_manager";
    }

    @PostMapping("/manager/book/{id}/edit")
    public String editBook(@ModelAttribute("book") Book book, Model model){
        productServiceImp.updateBook(book);
        return "redirect:/manager/book/catalog";
    }

    @GetMapping("/manager/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id, Model model){
        productServiceImp.deleteBook(id);
        return "redirect:/manager/book/catalog";
    }


    @GetMapping("/manager/discounts/view")
    public String discountsPage(Model model){
        model.addAttribute("discounts", discountServiceImp.getAllDiscounts());
        return "discounts_manager";
    }

    @GetMapping("/manager/discounts/add")
    public String addDiscountPage(Model model){
        model.addAttribute("discount", new Discount());
        return "add_discount_page";
    }

    @PostMapping("/manager/discounts/add")
    public String addDiscount(@ModelAttribute("discount") Discount discount, Model model){
        discountServiceImp.save(discount);
        return "redirect:/manager/discounts/view";
    }

    @GetMapping("/manager/discounts/{id}")
    public String discountPage(@PathVariable(name = "id") Long id, Model model){
        Discount discount = discountServiceImp.findById(id);
        model.addAttribute("discount", discount);
        return "discount_page_manager";
    }

    @PostMapping("/manager/discounts/{id}/edit")
    public String editDiscount(@ModelAttribute Discount discount, Model model){
        discountServiceImp.update(discount);
        return "redirect:/manager/discounts/view";
    }

    @GetMapping("/manager/discounts/{id}/delete")
    public String deleteDiscount(@ModelAttribute Discount discount, Model model){
        discountServiceImp.delete(discount);
        return "redirect:/manager/discounts/view";
    }

}
