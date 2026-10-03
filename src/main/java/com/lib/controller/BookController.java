package com.lib.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.request.BooksRequest;
import com.lib.dto.response.BookResponse;
import com.lib.service.BookService;
import com.lib.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Tag(name = "Books APIs")
public class BookController {

	private final BookService bookService;
	
	
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}


	@PostMapping("/add-book")
	@Operation(summary = "Add new book")
	public ResponseEntity<ApiResponse<BookResponse>> addBook(@Valid @RequestBody BooksRequest booksRequest) {
		BookResponse book = bookService.addBook(booksRequest);
		ApiResponse<BookResponse> apiResponse = new ApiResponse<BookResponse>(
					true,
					"SUCCESS",
					book
				);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/get-book/{id}")
	public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable String isbn) {
		BookResponse bookById = bookService.getBookById(isbn);
		ApiResponse<BookResponse> apiResponse = new ApiResponse<BookResponse>(
					true,
					"FOUND SUCCESSFULLY",
					bookById
				);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PatchMapping("/update-boo/{isbn}/{quantity}")
	public ResponseEntity<ApiResponse<BookResponse>> updateBook(@PathVariable String isbn, @PathVariable int quantity) {
		BookResponse updateBook = bookService.updateBook(isbn, quantity);
		ApiResponse<BookResponse> apiResponse = new ApiResponse<BookResponse>(
				true,
				"DATA UPDATED SUCCESSFULLY",
				updateBook
			);
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/delete-book")
	public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable String isbn) {
		bookService.deleteBook(isbn);
		ApiResponse<String> apiResponse = new ApiResponse<String>(
				true,
				"SUCCESS",
				"book deleted successfully"
			);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/search-book")
	public ResponseEntity<ApiResponse<List<BookResponse>>> searchBooks(@PathVariable String category){
		List<BookResponse> searchBooks = bookService.searchBooks(category);
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<List<BookResponse>>(
				true,
				"SUCCESS",
				searchBooks
			);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/avilabe-books")
	public ResponseEntity<ApiResponse<List<BookResponse>>> getAvailableBooks(@PathVariable String category){
		List<BookResponse> availableBooks = bookService.getAvailableBooks(category);
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<List<BookResponse>>(
				true,
				"SUCCESS",
				availableBooks
			);
		return ResponseEntity.ok(apiResponse);
	}
}
