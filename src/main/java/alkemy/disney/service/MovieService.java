package alkemy.disney.service;

import alkemy.disney.dto.MovieBasicDTO;
import alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieBasicDTO> getDetailsByFilter(String title, Long genre, String order);

    MovieDTO getDetailsById(Long movieId);

    MovieDTO saveMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long movieId, MovieDTO movieDTO);

    void deleteMovie(Long movieId);

    void saveCharacterInMovie(Long movieId, Long characterId);

    void deleteCharacterInMovie(Long movieId, Long characterId);

}
