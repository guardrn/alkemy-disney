package alkemy.disney.dto;

import alkemy.disney.entity.GenreEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDTO {

    private Long movieId;

    private String picture;

    private String title;

    private LocalDate releaseDate;

    private Integer rate;

    private List<CharacterDTO> characters;

    private GenreEntity genre;

    private boolean deleted;

}
