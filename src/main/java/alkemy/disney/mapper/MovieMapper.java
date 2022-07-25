package alkemy.disney.mapper;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieBasicDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.MovieEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    private GenreMapper genreMapper;

    private CharacterMapper characterMapper;

    public MovieMapper(@Lazy GenreMapper genreMapper, CharacterMapper characterMapper) {
        this.genreMapper = genreMapper;
        this.characterMapper = characterMapper;
    }

    public MovieBasicDTO movieEntity2BasicDTO(@NotNull MovieEntity entity) {
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setPicture(entity.getPicture());
        movieBasicDTO.setTitle(entity.getTitle());
        movieBasicDTO.setReleaseDate(entity.getReleaseDate());
        return movieBasicDTO;
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(@NotNull List<MovieEntity> entities) {
        List<MovieBasicDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtoList.add(movieEntity2BasicDTO(entity));
        }
        return dtoList;
    }

    public MovieDTO movieEntity2DTO(@NotNull MovieEntity movieEntity, boolean loadCharacters) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movieEntity.getMovieId());
        movieDTO.setPicture(movieEntity.getPicture());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setReleaseDate(movieEntity.getReleaseDate());
        movieDTO.setRate(movieEntity.getRate());
        movieDTO.setGenre(genreMapper.genreEntity2DTO(movieEntity.getGenre()));
        if (loadCharacters) {
            List<CharacterDTO> characters = characterMapper.characterEntityList2DTOList(
                    movieEntity.getCharacters(), false);
            movieDTO.setCharacters(characters);
        }
        return movieDTO;
    }

    public MovieEntity movieDTO2Entity(@NotNull MovieDTO movieDTO) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovieId(movieDTO.getMovieId());
        movieEntity.setPicture(movieDTO.getPicture());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setRate(movieDTO.getRate());
        movieEntity.setGenre(genreMapper.genreDTO2Entity(movieDTO.getGenre()));
        movieEntity.setCharacters(characterMapper.characterDTOList2EntityList(movieDTO.getCharacters()));
        return movieEntity;
    }

    public List<MovieEntity> movieDTOList2EntityList(@NotNull List<MovieDTO> dtoList) {
        List<MovieEntity> entityList = new ArrayList<>();
        for (MovieDTO movieDTO : dtoList) {
            entityList.add(movieDTO2Entity(movieDTO));
        }
        return entityList;
    }

    public List<MovieDTO> movieEntityList2DTOList(@NotNull List<MovieEntity> entities,
                                                  boolean loadCharacters) {
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtoList.add(movieEntity2DTO(entity, loadCharacters));
        }
        return dtoList;
    }

    public MovieEntity update(@NotNull MovieEntity movieEntity, @NotNull MovieDTO movieDTO) {
        movieEntity.setPicture(movieDTO.getPicture());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setRate(movieDTO.getRate());
        return movieEntity;
    }

}
