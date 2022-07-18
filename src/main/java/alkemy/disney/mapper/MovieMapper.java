package alkemy.disney.mapper;

import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    public MovieEntity movieDTO2Entity(MovieDTO movieDTO) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPicture(movieDTO.getPicture());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setReleaseDate(movieDTO.getReleaseDate());
        movieEntity.setRate(movieDTO.getRate());
        movieEntity.setCharacters(movieDTO.getCharacters());
        movieEntity.setGenre(movieDTO.getGenre());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movieEntity.getMovieId());
        movieDTO.setPicture(movieEntity.getPicture());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setReleaseDate(movieEntity.getReleaseDate());
        movieDTO.setRate(movieEntity.getRate());
        movieDTO.setCharacters(movieEntity.getCharacters());
        movieDTO.setGenre(movieEntity.getGenre());
        return movieDTO;
    }


    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities) {
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtoList.add(movieEntity2DTO(entity));
        }
        return dtoList;
    }

}
