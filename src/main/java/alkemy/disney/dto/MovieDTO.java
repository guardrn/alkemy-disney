package alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long movieId;

    @NotBlank(message = "Picture is mandatory")
    private String picture;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Release Date is mandatory")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate releaseDate;

    @NotNull(message = "Rate is mandatory")
    @Positive(message = "Rate cannot be negative")
    @Min(message = "Minimum rate is 1", value = 1)
    @Max(message = "Maximum rate is 5", value = 5)
    private Integer rate;

    private List<CharacterDTO> characters = new ArrayList<>();

    private GenreDTO genre;

    private boolean deleted = Boolean.FALSE;

}
