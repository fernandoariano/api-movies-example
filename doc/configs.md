# api-movies-example

## Configs

[back to Architecture](architecture.md)

---

## SwaggerConfig

This setting is essential for the `springfox-swagger-ui` dependency and generating the URL `http://localhost:8080/swagger-ui.html`

---

## HooksConfig

Installs a hook in the git repository that prevents the user from committing without all tests being OK

Whenever a commit is made, all tests are executed. If a test fails the commit is rejected.

---

[back to top](#api-movies-example)
