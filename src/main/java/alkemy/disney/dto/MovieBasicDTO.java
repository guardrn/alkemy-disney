package alkemy.disney.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovieBasicDTO {

    private String picture;

    private String title;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

}
