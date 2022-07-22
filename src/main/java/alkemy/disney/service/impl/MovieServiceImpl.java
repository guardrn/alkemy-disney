package alkemy.disney.service.impl;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieBasicDTO;
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

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieSpecification movieSpecification;

    private final MovieMapper movieMapper;

    private final CharacterService characterService;

    private final CharacterMapper characterMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieSpecification movieSpecification,
                            MovieMapper movieMapper,
                            CharacterService characterService,
                            CharacterMapper characterMapper) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<MovieBasicDTO> getDetailsByFilter(String title, Long genre, String order) {
        MovieFilterDTO filterDTO = new MovieFilterDTO(title, genre, order);
        List<MovieEntity> movies = movieRepository.findAll(movieSpecification.getByFilters(filterDTO));
        return movieMapper.movieEntityList2BasicDTOList(movies);
    }

    @Override
    public MovieDTO getDetailsById(Long movieId) throws EntityNotFoundException {
        MovieEntity movieFound = movieRepository.findById(movieId).orElseThrow(()
                -> new EntityNotFoundException("Find by ID: Movie not found"));
        return movieMapper.movieEntity2DTO(movieFound, true);
    }

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity movieSaved = movieRepository.save(movieEntity);
        return movieMapper.movieEntity2DTO(movieSaved, true);
    }

    @Override
    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) throws EntityNotFoundException {
        MovieEntity movieUpdated = movieRepository.findById(movieId).orElseThrow(()
                -> new EntityNotFoundException("Update: Movie not found"));
        movieMapper.update(movieUpdated, movieDTO);
        movieRepository.save(movieUpdated);
        return movieMapper.movieEntity2DTO(movieUpdated, true);
    }

    @Override
    public void deleteMovie(Long movieId) throws EntityNotFoundException {
        movieRepository.findById(movieId).orElseThrow(()
                -> new EntityNotFoundException("Delete: Movie not found"));
        movieRepository.deleteById(movieId);
    }

    // TODO: TEST DEL
    @Override
    public void saveCharacterInMovie(Long movieId, Long characterId) {
        MovieDTO movieDTO = getDetailsById(movieId);
        MovieEntity movieEntitySaved = movieMapper.movieDTO2Entity(movieDTO);
        CharacterDTO characterDTO = characterService.getDetailsById(characterId);
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        movieEntitySaved.saveCharacterInMovie(characterEntity);
        movieRepository.save(movieEntitySaved);
    }

    // TODO: TEST DEL
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
