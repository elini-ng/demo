package com.estsoft.demo.service;

import com.estsoft.demo.domain.Book;
import com.estsoft.demo.dto.AddBookRequest;
import com.estsoft.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new NoSuchElementException("Id doesn't exist: " + id));
    }

    public Book saveBook(AddBookRequest request) {
        return bookRepository.save(request.toEntity());
    }
}
