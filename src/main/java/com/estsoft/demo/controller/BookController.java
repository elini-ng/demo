package com.estsoft.demo.controller;

import com.estsoft.demo.domain.Book;
import com.estsoft.demo.dto.AddBookRequest;
import com.estsoft.demo.dto.BookViewResponse;
import com.estsoft.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<BookViewResponse> bookList = bookService.getBookList()
                .stream()
                .map(BookViewResponse::new)
                .toList();
        model.addAttribute("bookList", bookList);

        return "bookManager";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    public String getBookDetail(@PathVariable("id") String id,
                                Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", new BookViewResponse(book));

        return "bookDetail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public String addBook(@ModelAttribute AddBookRequest request) {
        bookService.saveBook(request);

        return "redirect:/books";
    }
}
