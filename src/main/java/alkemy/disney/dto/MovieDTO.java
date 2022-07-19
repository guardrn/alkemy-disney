package alkemy.disney.dto;

import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
