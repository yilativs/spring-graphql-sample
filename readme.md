***A sample graphql application***

Demonstrates how to use graphql:
mapping
declaring sustom types
avoiding n+1 select
testing



Open at http://localhost:8080/graphiql?path=/graphql


Query to Use:

```
query bookDetails {
  books(sortAsc: true) {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
    reviews{
      id
      text
      date
    }
  }
}
```

TODO
 add mutators 
 add integration tests via https://www.baeldung.com/java-call-graphql-service

Useful links:

[Spring Guide](https://spring.io/guides/gs/graphql-server/)

[graphql-java](https://www.graphql-java.com)

[Spring Docs](https://docs.spring.io/spring-graphql/docs/current/reference/html)

[How to avoid N+1](https://techdozo.dev/spring-for-graphql-how-to-solve-the-n1-problem/)
It allows to avoid N+1 only when loading one root object (TODO Find out how to load multiple related objects when loading multiple root objects e.g books->author)

[GraphQL Video Course](https://www.youtube.com/playlist?list=PLiwhu8iLxKwL1TU0RMM6z7TtkyW-3-5Wi)