package alkemy.disney.dto;

import lombok.Data;

@Data
public class MovieFilterDTO {

    private String title;

    private Long genre;

    private String order;

    public MovieFilterDTO(String title, Long genre, String order) {
        this.title = title;
        this.genre = genre;
        this.order = order;
    }

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }

}
