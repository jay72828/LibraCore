package com.lib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lib.entiity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

	Optional<Books> findByIsbn(String isbn);
	void deleteByIsbn(String isbn);
	List<Books> findByCategory(String category);
}
