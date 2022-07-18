package alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE genre_id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
public class GenreEntity {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column
    private String name;

    @Column
    private String picture;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;

    @Column
    private boolean deleted = Boolean.FALSE;

}
