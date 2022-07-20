package alkemy.disney.service;

import alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> getAllMovies();

    MovieDTO getDetailsById(Long movieId);

    List<MovieDTO> getDetailsByFilter(String title, Long genre, String order);

    MovieDTO saveMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long movieId, MovieDTO movieDTO);

    void deleteMovie(Long movieId);

    void saveCharacterInMovie(Long movieId, Long characterId);

    void deleteCharacterInMovie(Long movieId, Long characterId);

}
