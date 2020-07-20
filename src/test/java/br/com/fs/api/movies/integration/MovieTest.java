package br.com.fs.api.movies.integration;

import br.com.fs.api.movies.TestUtil;
import br.com.fs.api.movies.controller.MovieController;
import br.com.fs.api.movies.controller.impl.MovieControllerImpl;
import br.com.fs.api.movies.model.mapper.ActorMapperImpl;
import br.com.fs.api.movies.model.mapper.DirectorMapperImpl;
import br.com.fs.api.movies.model.mapper.MovieMapperImpl;
import br.com.fs.api.movies.repository.MovieRepository;
import br.com.fs.api.movies.service.MovieService;
import br.com.fs.api.movies.templates.dto.MovieDtoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
  MovieController.class,
  MovieControllerImpl.class,
  MovieMapperImpl.class,
  ActorMapperImpl.class,
  DirectorMapperImpl.class,
  MovieService.class,
  MovieRepository.class
})
@AutoConfigureMockMvc()
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureDataMongo
@Slf4j
public class MovieTest {

  public static final String BASE_URL = "http://localhost:8080/movies";

  private final TestUtil testUtil = TestUtil.getInstance();

  private final MovieDtoTemplate movieDtoTemplate = MovieDtoTemplate.getInstance();

  @Autowired
  protected MockMvc mvc;

  @Test
  public void testSave() throws Exception {
    var movieDto = movieDtoTemplate.getNew();

    final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
      .post(BASE_URL)
      .content(testUtil.toJson(movieDto))
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isCreated())
      .andReturn();

    final String response = mvcResult.getResponse().getContentAsString();
    log.info(response);

    assertThat(response, hasJsonPath("$", Matchers.notNullValue()));
    assertThat(response, hasJsonPath("$.id", Matchers.notNullValue()));
  }


}