package org.sample.foo.web;

import org.junit.jupiter.api.Test;
import org.sample.foo.config.GraphQlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

@Import(GraphQlConfig.class)
@GraphQlTest(BookController.class)
public class BookControllerTests {

	@Autowired
	private GraphQlTester graphQlTester;

	@Test
	void shouldGetFirstBook() {
		this.graphQlTester
				.documentName("bookDetails")
				.variable("id", "book-1")
				.execute()
				.path("bookById")
				.matchesJson("""
						    {
						        "id": "book-1",
						        "name": "Effective Java",
						        "pageCount": 416,
						        "author": {
						          "firstName": "Joshua",
						          "lastName": "Bloch"
						        }
						    }
						""");
	}
}