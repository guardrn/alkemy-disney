package alkemy.disney.service.impl;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.dto.MovieFilterDTO;
import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.entity.MovieEntity;
import alkemy.disney.mapper.CharacterMapper;
import alkemy.disney.mapper.MovieMapper;
import alkemy.disney.repository.MovieRepository;
import alkemy.disney.repository.specification.MovieSpecification;
import alkemy.disney.service.CharacterService;
import alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private MovieSpecification movieSpecification;

    private MovieMapper movieMapper;

    private CharacterService characterService;

    private CharacterMapper characterMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieSpecification movieSpecification,
                            MovieMapper movieMapper, CharacterService characterService,
                            CharacterMapper characterMapper) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        return movieMapper.movieEntityList2DTOList(entities, false);
    }

    @Override
    public MovieDTO getDetailsById(Long movieId) {
        MovieEntity movieEntity = movieRepository.findById(movieId).orElse(null);
        return movieMapper.movieEntity2DTO(movieEntity, true);
    }

    @Override
    public List<MovieDTO> getDetailsByFilter(String title, Long genre, String order) {
        MovieFilterDTO filterDTO = new MovieFilterDTO(title, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filterDTO));
        return movieMapper.movieEntityList2DTOList(entities, true);
    }

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity movieEntitySaved = movieRepository.save(movieEntity);
        return movieMapper.movieEntity2DTO(movieEntitySaved, false);
    }

    @Override
    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) {
        MovieEntity movieEntity = movieRepository.getReferenceById(movieId);
        movieMapper.update(movieEntity, movieDTO);
        movieRepository.save(movieEntity);
        return movieMapper.movieEntity2DTO(movieEntity, false);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public void saveCharacterInMovie(Long movieId, Long characterId) {
        MovieDTO movieDTO = getDetailsById(movieId);
        MovieEntity movieEntitySaved = movieMapper.movieDTO2Entity(movieDTO);
        CharacterDTO characterDTO = characterService.getDetailsById(characterId);
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        movieEntitySaved.saveCharacterInMovie(characterEntity);
        movieRepository.save(movieEntitySaved);
    }

    @Override
    public void deleteCharacterInMovie(Long movieId, Long characterId) {
        MovieDTO movieDTO = getDetailsById(movieId);
        MovieEntity movieEntityDeleted = movieMapper.movieDTO2Entity(movieDTO);
        CharacterDTO characterDTO = characterService.getDetailsById(characterId);
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        movieEntityDeleted.deleteCharacterInMovie(characterEntity);
        movieRepository.save(movieEntityDeleted);
    }

}
