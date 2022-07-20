package alkemy.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE character_id = ?")
@Where(clause = "deleted = false")
@Data
public class CharacterEntity {

    @Id
    @Column(name = "character_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @Column
    private String picture;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Double weight;

    @Column
    private String story;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;

    @Column
    private boolean deleted = Boolean.FALSE;

}
