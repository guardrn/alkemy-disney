package alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE movie_id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
public class MovieEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column
    private String picture;

    @Column
    private String title;

    @Column
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

    @Column
    private Integer rate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movies_characters",
            joinColumns = {@JoinColumn(name = "movie_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "character_id", nullable = false)}
    )
    private List<CharacterEntity> characters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genre;

    @Column
    private boolean deleted = Boolean.FALSE;

    public void saveCharacterInMovie(CharacterEntity character) {
        characters.add(character);
    }

    public void deleteCharacterInMovie(CharacterEntity character) {
        characters.remove(character);
    }

}
