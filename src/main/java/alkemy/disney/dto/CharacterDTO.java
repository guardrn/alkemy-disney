package alkemy.disney.dto;

import alkemy.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private Long characterId;

    private String picture;

    private String name;

    private Integer age;

    private Double weight;

    private String story;

    private List<MovieDTO> movies;

    private boolean deleted;

}
