package alkemy.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE movie_id = ?")
@Where(clause = "deleted = false")
@Data
public class MovieEntity {

    @Id
    @Column(name = "movie_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Integer rate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_characters",
            joinColumns = {@JoinColumn(name = "movie_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "character_id", nullable = false)}
    )
    private List<CharacterEntity> characters = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    public void saveCharacterInMovie(CharacterEntity character) {
        characters.add(character);
    }

    public void deleteCharacterInMovie(CharacterEntity character) {
        characters.remove(character);
    }

}
