package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Book;

public class BookFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		validateBook((Book) obj, errors);
	}
	
	public void validateBook(Book book, Errors errors) {
		errors.setNestedPath("book");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.name", "NAME_REQUIRED", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.author", "AUTHOR_REQUIRED", "Author is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.publisher", "PUBLISHER_REQUIRED", "Publisher is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.date", "DATE_REQUIRED", "Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.price", "PRICE_REQUIRED", "Price is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "book.condition", "CONDITION_REQUIRED", "Condition is required.");
		errors.setNestedPath("");
	}

}
