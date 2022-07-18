package alkemy.disney.dto;

import alkemy.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private Long genreId;

    private String name;

    private String picture;

    private List<MovieEntity> movies;

    private boolean deleted;

}
