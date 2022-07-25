package alkemy.disney.mapper;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.entity.GenreEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    private MovieMapper movieMapper;

    @Autowired
    public GenreMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public GenreDTO genreEntity2DTO(@NotNull GenreEntity genreEntity) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenreId(genreEntity.getGenreId());
        genreDTO.setName(genreEntity.getName());
        genreDTO.setPicture(genreEntity.getPicture());
        return genreDTO;
    }

    public List<GenreDTO> genreEntityList2DTOList(@NotNull List<GenreEntity> entities) {
        List<GenreDTO> dtoList = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtoList.add(genreEntity2DTO(entity));
        }
        return dtoList;
    }

    public GenreEntity genreDTO2Entity(@NotNull GenreDTO genreDTO) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreId(genreDTO.getGenreId());
        genreEntity.setName(genreDTO.getName());
        genreEntity.setPicture(genreDTO.getPicture());
        genreEntity.setMovies(movieMapper.movieDTOList2EntityList(genreDTO.getMovies()));
        return genreEntity;
    }

    public GenreEntity update(@NotNull GenreEntity genreEntity, @NotNull GenreDTO genreDTO) {
        genreEntity.setName(genreDTO.getName());
        genreEntity.setPicture(genreDTO.getPicture());
        return genreEntity;
    }

}
