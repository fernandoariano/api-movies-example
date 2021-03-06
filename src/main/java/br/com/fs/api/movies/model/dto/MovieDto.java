package br.com.fs.api.movies.model.dto;

import br.com.fs.api.movies.model.Censorship;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

  @ApiModelProperty(position = -100)
  private String id;

  @Indexed
  @NotBlank
  @Size(min = 1, max = 100)
  @ApiModelProperty(example = "Era uma Vez em... Hollywood", position = -50)
  private String name;

  @ApiModelProperty(example = "2019-08-15")
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate released;

  @NotNull
  private Censorship censorship;

  @Valid
  @NotNull
  @ApiModelProperty(position = -25)
  private DirectorDto director;

  @Valid
  @NotNull
  @NotEmpty
  @Size(max = 10)
  private List<ActorDto> cast;

}
