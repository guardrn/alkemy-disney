package alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicDTO {

    private String picture;

    private String title;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

}
