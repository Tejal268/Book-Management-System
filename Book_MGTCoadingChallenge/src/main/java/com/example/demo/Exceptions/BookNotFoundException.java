package com.example.demo.Exceptions;

public class BookNotFoundException  extends RuntimeException{

	public BookNotFoundException(String msg) {
		super(msg);
	}
}
