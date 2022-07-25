package alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private Long characterId;

    @NotBlank(message = "Picture is mandatory")
    private String picture;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Age is mandatory")
    @Positive(message = "Age cannot be lower than 0")
    private Integer age;

    @NotNull(message = "Weight is mandatory")
    @Positive(message = "Weight cannot be lower than 0")
    private Double weight;

    @NotBlank(message = "Story is mandatory")
    private String story;

    private List<MovieDTO> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

}
