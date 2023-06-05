package org.sample.foo.model;

import java.util.List;
import java.util.Map;

public record Book(String id, String name, int pageCount, String authorId, List<Integer> reviewIds) {

	private static Map<String, Book> idTobook = Map.of(
			"book-1", new Book("book-1", "Effective Java", 416, "author-1", List.of(1)),
			"book-2", new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2", List.of(2)),
			"book-3", new Book("book-3", "Down Under", 436, "author-3", List.of(3, 4)));

	public static Book getById(String id) {
		return idTobook.get(id);
	}

	public static List<Book> books(boolean sortAsc) {
		return idTobook.values().stream().sorted((b1, b2) -> sortAsc ? b1.name().compareTo(b2.name()) : b2.name().compareTo(b1.name())).toList();
	}
}