package alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CharacterFilterDTO {

    private String name;

    private Integer age;

    private List<Long> movies;

}
