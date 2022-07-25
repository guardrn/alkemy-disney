package alkemy.disney.mapper;

import alkemy.disney.dto.CharacterBasicDTO;
import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.entity.CharacterEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    private MovieMapper movieMapper;

    @Autowired
    public CharacterMapper(@Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public CharacterBasicDTO characterEntity2BasicDTO(@NotNull CharacterEntity entity) {
        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setPicture(entity.getPicture());
        characterBasicDTO.setName(entity.getName());
        return characterBasicDTO;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(@NotNull List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtoList = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtoList.add(characterEntity2BasicDTO(entity));
        }
        return dtoList;
    }

    public CharacterDTO characterEntity2DTO(@NotNull CharacterEntity characterEntity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setCharacterId(characterEntity.getCharacterId());
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

    public List<CharacterDTO> characterEntityList2DTOList(@NotNull List<CharacterEntity> entities,
                                                          boolean loadMovies) {
        List<CharacterDTO> dtoList = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtoList.add(characterEntity2DTO(entity, loadMovies));
        }
        return dtoList;
    }

    public CharacterEntity characterDTO2Entity(@NotNull CharacterDTO characterDTO) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        return characterEntity;
    }

    public List<CharacterEntity> characterDTOList2EntityList(@NotNull List<CharacterDTO> dtoList) {
        List<CharacterEntity> entities = new ArrayList<>();
        for (CharacterDTO dto : dtoList) {
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }

    public CharacterEntity update(@NotNull CharacterEntity characterEntity,
                                  @NotNull CharacterDTO characterDTO) {
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        return characterEntity;
    }

}
