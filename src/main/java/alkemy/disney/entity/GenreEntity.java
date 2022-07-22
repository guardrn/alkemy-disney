package alkemy.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE genre_id = ?")
@Where(clause = "deleted = false")
@Data
public class GenreEntity {

    @Id
    @Column(name = "genre_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String picture;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MovieEntity> movies = new ArrayList<>();

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

}
