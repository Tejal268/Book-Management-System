package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Repo.BookRepository;


@Service
public class MyService {

    @Autowired
    private BookRepository repo;

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookByIsbn(Long isbn) {
        return repo.findById(isbn)
                   .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
    }

    public String addBook(Book book) {
        repo.save(book);
        return "Book added successfully";
    }

    public Book updateBook(Long isbn, Book book) {
        Book existing = getBookByIsbn(isbn);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublicationYear(book.getPublicationYear());
        return repo.save(existing);
    }

    public void deleteBook(Long isbn) {
        if (!repo.existsById(isbn)) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        repo.deleteById(isbn);
    }
}
