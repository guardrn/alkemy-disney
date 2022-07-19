package alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFilterDTO {

    private String name;

    private Integer age;

    private List<Long> movies;

    public CharacterFilterDTO(String name, Integer age, List<Long> movies) {
        this.name = name;
        this.age = age;
        this.movies = movies;
    }

}
