package alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private Long genreId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Picture is mandatory")
    private String picture;

    private List<MovieDTO> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

}
