package com.lib.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lib.dto.request.BooksRequest;
import com.lib.dto.response.BookResponse;
import com.lib.entiity.Books;

@Component
public class BookMapper {

	public Books toEntity(BooksRequest booksRequest) {
		Books books = new Books();
		books.setTitle(booksRequest.getTitle());
		books.setAuthor(booksRequest.getAuthor());
		books.setIsbn(booksRequest.getIsbn());
		books.setCategory(booksRequest.getCategory());
		books.setQuantity(booksRequest.getQuantity());
		books.setAvailableQuantity(booksRequest.getAvailableQuantity());
		return books;
	}
	
	public BookResponse toResponse(Books books) {
		
		BookResponse response = new BookResponse();
		response.setId(books.getId());
		response.setTitle(books.getTitle());
		response.setAuthor(books.getAuthor());
		response.setIsbn(books.getIsbn());
		response.setCategory(books.getCategory());
		response.setQuantity(books.getQuantity());
		response.setAvailableQuantity(books.getAvailableQuantity());
		response.setAvailable(books.isAvailable());
		response.setCreatedAt(LocalDateTime.now().toString());
		response.setUpdatedAt(LocalDateTime.now().toString());
		return response;
	}
	
	public List<BookResponse> toResponse(List<Books> books) {
		List<BookResponse> response = new ArrayList<>();
		books.forEach(book-> response.add(toResponse(book)));
		return response;
	}
}
