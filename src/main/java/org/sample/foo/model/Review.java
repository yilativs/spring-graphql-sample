package org.sample.foo.model;

import java.time.LocalDate;
import java.util.Map;

public record Review(Integer id, String text, LocalDate date) {

	private static Map<Integer, Review> idToReview = Map.of(
			1, new Review(1, "ok", LocalDate.now()),
			2, new Review(2, "not bad", LocalDate.now()),
			3, new Review(3, "bad", LocalDate.now()),
			4, new Review(4, "horrible", LocalDate.now()));

	public static Review getById(Integer id) {
		return idToReview.get(id);
	}

}
