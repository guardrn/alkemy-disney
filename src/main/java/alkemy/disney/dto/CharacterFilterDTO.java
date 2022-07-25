package alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CharacterFilterDTO {

    private String name;

    private Integer age;

    private List<Long> movies;

}
