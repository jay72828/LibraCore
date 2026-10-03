package com.lib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

	private Long id;

	private String title;

	private String author;

	private String isbn;

	private String category;

	private int quantity;

	private int availableQuantity;

	private boolean available;

	private String createdAt;

	private String updatedAt;
	
}
