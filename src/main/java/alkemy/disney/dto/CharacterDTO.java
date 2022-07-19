package alkemy.disney.dto;

import lombok.Data;

import java.util.List;

@Data
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
