package com.lib.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksRequest {

	 @NotBlank(message = "Title is required")
	    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
	    private String title;

	    @NotBlank(message = "Author is required")
	    @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
	    private String author;

	    @NotBlank(message = "ISBN is required")
	    @Size(min = 10, max = 17, message = "Invalid ISBN")
	    private String isbn;

	    @NotBlank(message = "Category is required")
	    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
	    private String category;

	    @Positive
	    @Min(value = 1, message = "Quantity must be at least 1")
	    private int quantity;

	    @Positive
	    @Min(value = 0, message = "Available quantity cannot be negative")
	    private int availableQuantity;
}
