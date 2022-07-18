package alkemy.disney.repository;

import alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    MovieEntity getDetailsByName(String name);

    MovieEntity getDetailsByGenre(Long genre);

    // TODO: QUERIES
    List<MovieEntity> getAllMoviesByOrderASC();

    // TODO: QUERIES
    List<MovieEntity> getAllMoviesByOrderDESC();

}
