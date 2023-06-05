package org.sample.foo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sample.foo.model.Author;
import org.sample.foo.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

	@SchemaMapping
	public Author author(Book book) {
		return Author.getById(book.authorId());
	}

	
	//see https://techdozo.dev/spring-for-graphql-how-to-solve-the-n1-problem/
	//field = "authorId" can be omitted
	@BatchMapping(field = "authorId", typeName = "Book")
	public Map<Book, Author> authors(List<Book> books) {
		// in reality it should be a select with join.
		Map<Book, Author> bookToAuthor = new HashMap<>();
		for(Book book : books) {
			bookToAuthor.put(book, Author.getById(book.authorId()));
		}
		return bookToAuthor;
	}

}