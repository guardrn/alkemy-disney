package alkemy.disney.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
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
    @Min(1)
    @Max(5)
    private Integer rate;

    private List<CharacterDTO> characters = new ArrayList<>();

    private GenreDTO genre;

    private boolean deleted = Boolean.FALSE;

}
