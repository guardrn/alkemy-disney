package alkemy.disney.mapper;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO genreDTO) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(genreDTO.getName());
        genreEntity.setPicture(genreDTO.getPicture());
        genreEntity.setMovies(genreDTO.getMovies());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genreEntity) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenreId(genreEntity.getGenreId());
        genreDTO.setName(genreEntity.getName());
        genreDTO.setPicture(genreEntity.getPicture());
        genreDTO.setMovies(genreEntity.getMovies());
        return genreDTO;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtoList = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtoList.add(genreEntity2DTO(entity));
        }
        return dtoList;
    }

    public GenreEntity update(GenreEntity genreEntity, GenreDTO genreDTO) {
        genreEntity.setName(genreDTO.getName());
        genreEntity.setPicture(genreDTO.getPicture());
        return genreEntity;
    }

}
