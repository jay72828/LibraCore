package com.lib.service;

import java.util.List;

import com.lib.dto.request.BooksRequest;
import com.lib.dto.response.BookResponse;

public interface BookService {

	BookResponse addBook(BooksRequest bookRequest);

	BookResponse getBookById(String isbn);

	BookResponse updateBook(String isbn, int quantity);

	void deleteBook(String isbn);

	List<BookResponse> searchBooks(String category);

	List<BookResponse> getAvailableBooks(String category);

}
