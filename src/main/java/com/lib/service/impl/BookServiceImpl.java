package com.lib.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lib.dto.request.BooksRequest;
import com.lib.dto.response.BookResponse;
import com.lib.entiity.Books;
import com.lib.exception.BadRequestException;
import com.lib.exception.ResourceNotFoundException;
import com.lib.mapper.BookMapper;
import com.lib.repository.BooksRepository;
import com.lib.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private final BooksRepository booksRepository;
	private final BookMapper bookMapper;

	public BookServiceImpl(BooksRepository booksRepository, BookMapper bookMapper) {
		super();
		this.booksRepository = booksRepository;
		this.bookMapper = bookMapper;
	}

	@Override
	public BookResponse addBook(BooksRequest bookRequest) {
		Optional<Books> byIsbn = booksRepository.findByIsbn(bookRequest.getIsbn());
		if(byIsbn.isPresent()) {
			throw new BadRequestException("allready exists this books !");
		}
		Books entity = bookMapper.toEntity(bookRequest);
		Books save = booksRepository.save(entity);
		return bookMapper.toResponse(save);
	}
	
	
	@Override
	public BookResponse getBookById(String isbn) {
		 Books books = booksRepository.findByIsbn(isbn).orElseThrow(()-> new ResourceNotFoundException("Id not found"+isbn));
		 return bookMapper.toResponse(books);
	}

	
	@Override
	public BookResponse updateBook(String isbn, int quantity) {
		Books books = booksRepository.findByIsbn(isbn).orElseThrow(()-> new ResourceNotFoundException("Book not found this id "+ isbn));
		
		if(quantity != 0) {
			books.setQuantity(quantity);
			books.setAvailableQuantity(quantity);
			Books save = booksRepository.save(books);
			return bookMapper.toResponse(save);
		}
		return null;
	}

	@Override
	public void deleteBook(String isbn) {
		getBookById(isbn);
		booksRepository.deleteByIsbn(isbn);
	}

	@Override
	public List<BookResponse> searchBooks(String category) {
		List<Books> byCategory = booksRepository.findByCategory(category);
		return bookMapper.toResponse(byCategory);
	}


	@Override
	public List<BookResponse> getAvailableBooks(String category) {
		List<Books> books = booksRepository.findAll();
		 List<Books> availableBooks = books.stream()
		            .filter(book -> book.getAvailableQuantity() > 0)
		            .toList();
		 return bookMapper.toResponse(availableBooks);
	}

}
