package alkemy.disney.service.impl;

import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.MovieEntity;
import alkemy.disney.mapper.MovieMapper;
import alkemy.disney.repository.MovieRepository;
import alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(entities);
        return movies;
    }

    @Override
    public MovieDTO getDetailsById(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElse(null);
        MovieDTO movieFound = movieMapper.movieEntity2DTO(movieEntity);
        return movieFound;
    }

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity movieEntitySaved = movieRepository.save(movieEntity);
        MovieDTO movieSaved = movieMapper.movieEntity2DTO(movieEntitySaved);
        return movieSaved;
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        return null;
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO getDetailsByName(String name) {
        MovieEntity movieEntity = movieRepository.getDetailsByName(name);
        MovieDTO movieFound = movieMapper.movieEntity2DTO(movieEntity);
        return movieFound;
    }

    @Override
    public MovieDTO getDetailsByGenre(Long genre) {
        MovieEntity movieEntity = movieRepository.getDetailsByGenre(genre);
        MovieDTO movieFound = movieMapper.movieEntity2DTO(movieEntity);
        return movieFound;
    }

    @Override
    public List<MovieDTO> getAllMoviesByOrder(String order) {
        List<MovieEntity> entities;
        if (order.equals("ASC")) {
            entities = movieRepository.getAllMoviesByOrderASC();
        } else {
            entities = movieRepository.getAllMoviesByOrderDESC();
        }
        List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(entities);
        return movies;
    }

}
