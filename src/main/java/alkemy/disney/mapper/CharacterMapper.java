package alkemy.disney.mapper;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    @Lazy
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity characterEntity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setPicture(characterEntity.getPicture());
        characterDTO.setName(characterEntity.getName());
        characterDTO.setAge(characterEntity.getAge());
        characterDTO.setWeight(characterEntity.getWeight());
        characterDTO.setStory(characterEntity.getStory());
        if (loadMovies) {
            List<MovieDTO> movies = movieMapper.movieEntityList2DTOList
                    (characterEntity.getMovies(), false);
            characterDTO.setMovies(movies);
        }
        return characterDTO;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtoList) {
        List<CharacterEntity> entities = new ArrayList<>();
        for (CharacterDTO dto : dtoList) {
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities,
                                                          boolean loadMovies) {
        List<CharacterDTO> dtoList = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtoList.add(characterEntity2DTO(entity, loadMovies));
        }
        return dtoList;
    }

    public CharacterEntity update(CharacterEntity characterEntity, CharacterDTO characterDTO) {
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        return characterEntity;
    }

}
