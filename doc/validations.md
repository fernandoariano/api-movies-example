# api-movies-example

## Constraints

[back to Architecture](architecture.md)

---

### Validating user input

The Spring Boot provides a [starter](https://www.baeldung.com/spring-boot-bean-validation) to work with the specification, JSR 380, also known as [Bean Validation 2.0](https://beanvalidation.org/2.0/)

_As an example for, it is interesting to mention the `name` field of the classes `ActorDto` and `DirectorDto` , since a regex type validation was used_

```java
  @Pattern(regexp = "^\p{L}[\p{L} .'-]*$")
  private String name;
```

**Sample of Valid Names:**

```
Flávio Sousa
Flavio L. Sousa
Peter Müller
François Hollande
Patrick O'Brian
Silvana Koch-Mehrin
```

**Sample of Invalid Names:**

```
Flávio Sousa 1
 Flávio Sousa
123 456
Flávio Sousa?
Flávio! Sousa
```

### Business Validations

Business validations are performed at the `service` layer.

Are normalized to application exceptions to facilitate future maintenances.

An example is the exception `ApiMovieValidationException`, thrown when in an attempt to save a movie it was previously saved in the database with another `id`.
(_Note: With same `id` the document is updated_)

---

[back to top](#api-movies-example)
