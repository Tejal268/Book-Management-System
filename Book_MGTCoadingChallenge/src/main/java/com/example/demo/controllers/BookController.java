package com.example.demo.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.service.MyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/books")
public class BookController {
	

	@Autowired
	private MyService service;

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable Long isbn) {
        return service.getBookByIsbn(isbn);
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        return service.addBook(book); 
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable Long isbn, @Valid @RequestBody Book book) {
        return service.updateBook(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long isbn) {
        service.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}
