package alkemy.disney.repository;

import alkemy.disney.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    CharacterEntity getDetailsByName(String name);

    CharacterEntity getDetailsByMovie(Long idMovie);

}
