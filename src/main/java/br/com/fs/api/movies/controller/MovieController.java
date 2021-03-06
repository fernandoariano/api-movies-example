package br.com.fs.api.movies.controller;

import br.com.fs.api.movies.model.dto.MovieDto;
import br.com.fs.api.movies.model.dto.filter.MovieFilterDto;
import br.com.fs.api.movies.model.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Movies", tags = "Movies")
@RequestMapping("/movies")
public interface MovieController {

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update a Movie", response = MovieDto.class)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Updated", response = MovieDto.class),
    @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
  })
  MovieDto update(@Valid @RequestBody MovieDto request, @PathVariable String id);

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a new Movie", response = MovieDto.class)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Created", response = MovieDto.class),
    @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
  })
  MovieDto create(@Valid @RequestBody MovieDto request);

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Retrieve a list of Movies", response = MovieDto.class)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = MovieDto.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
  })
  List<MovieDto> findMovies(@Validated MovieFilterDto filter);

}
