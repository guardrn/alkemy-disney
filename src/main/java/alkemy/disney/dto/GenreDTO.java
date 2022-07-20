package alkemy.disney.dto;

import alkemy.disney.entity.MovieEntity;
import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {

    private Long genreId;

    private String name;

    private String picture;

    private List<MovieEntity> movies;

    private boolean deleted;

}
