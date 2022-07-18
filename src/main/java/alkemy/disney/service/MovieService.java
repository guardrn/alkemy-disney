package alkemy.disney.service;

import alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> getAllMovies();

    MovieDTO getDetailsById(Long id);

    MovieDTO saveMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovie(Long id);

    MovieDTO getDetailsByName(String name);

    MovieDTO getDetailsByGenre(Long genre);

    List<MovieDTO> getAllMoviesByOrder(String order);

}
