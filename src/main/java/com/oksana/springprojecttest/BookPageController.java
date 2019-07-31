package com.oksana.springprojecttest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class BookPageController {

    private final BookService bookService;

    public BookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = GET)
    public String books(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(value = "books", method = POST)
    public String name(String name, Model model) {
        this.bookService.create(name);
        return books(model);
    }

    @RequestMapping(value = "book/{id}", method = GET)
    public String getBook(@PathVariable Long id, Model model) {
        Book book = this.bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "book", method = POST)
    public String updateBook(Long id, Model model, String name) {
        Book book = this.bookService.update(id, name);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "book/{id}/delete", method = GET)
    public String deleteBook(@PathVariable Long id, Model model) {
        boolean deleteBook = this.bookService.deleteBook(id);
        String message = (deleteBook) ? String.format("Book %d was successfully deleted!", id) : String.format("Error : %d", id);
        model.addAttribute("message", message);
        return "message";
    }
}
