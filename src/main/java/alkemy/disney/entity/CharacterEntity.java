package alkemy.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE character_id = ?")
@Where(clause = "deleted = false")
@Data
public class CharacterEntity {

    @Id
    @Column(name = "character_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String story;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<MovieEntity> movies = new ArrayList<>();

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

}
