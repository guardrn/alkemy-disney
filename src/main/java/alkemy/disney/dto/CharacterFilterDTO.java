package alkemy.disney.dto;

import lombok.Data;

import java.util.List;

@Data
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
