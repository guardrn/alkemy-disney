package alkemy.disney.mapper;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO movieDTO) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPicture(movieDTO.getPicture());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setRate(movieDTO.getRate());
        movieEntity.setGenre(movieDTO.getGenre());
        List<CharacterEntity> characters = characterMapper.characterDTOList2EntityList(
                movieDTO.getCharacters());
        movieEntity.setCharacters(characters);
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean loadCharacters) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movieEntity.getMovieId());
        movieDTO.setPicture(movieEntity.getPicture());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setReleaseDate(movieEntity.getReleaseDate());
        movieDTO.setRate(movieEntity.getRate());
        movieDTO.setGenre(movieEntity.getGenre());
        if (loadCharacters) {
            List<CharacterDTO> characters = characterMapper.characterEntityList2DTOList(
                    movieEntity.getCharacters(), false);
            movieDTO.setCharacters(characters);
        }
        return movieDTO;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtoList.add(movieEntity2DTO(entity, loadCharacters));
        }
        return dtoList;
    }

    public MovieEntity update(MovieEntity movieEntity, MovieDTO movieDTO){
        movieEntity.setPicture(movieDTO.getPicture());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setRate(movieDTO.getRate());
        return movieEntity;
    }

}
