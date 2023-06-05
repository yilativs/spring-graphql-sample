package org.sample.foo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sample.foo.model.Author;
import org.sample.foo.model.Book;
import org.sample.foo.model.Review;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

	@QueryMapping
	public List<Book> books(@Argument boolean sortAsc) {
		return Book.books(sortAsc);
	}

	@QueryMapping
	public Book bookById(@Argument String id) {
		return Book.getById(id);
	}
	/*
	 * @SchemaMapping public Author author(Book book) { return
	 * Author.getById(book.authorId()); }
	 */

	@BatchMapping(field = "author", typeName = "Book")
	public Map<Book, Author> author(List<Book> books) {
		Map<Book, Author> bookToAuthor = new HashMap<>();
		for (Book book : books) {
			bookToAuthor.put(book, Author.getById(book.authorId()));
		}
		return bookToAuthor;
	}

	// see https://techdozo.dev/spring-for-graphql-how-to-solve-the-n1-problem/
	// field = "authorId" can be omitted
	@BatchMapping(field = "reviews", typeName = "Book")
	public Map<Book, List<Review>> reviews(List<Book> books) {
		// in reality it should be a select with join.
			Map<Book, List<Review>> bookToReviews = new HashMap<>();
		for (Book book : books) {
			List<Review> reviews = new ArrayList<>();
			for (Integer reviewId : book.reviewIds()) {
				reviews.add(Review.getById(reviewId));
			}
			bookToReviews.put(book, reviews);
		}
		return bookToReviews;
	}

}