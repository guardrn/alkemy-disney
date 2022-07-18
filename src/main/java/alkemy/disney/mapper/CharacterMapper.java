package alkemy.disney.mapper;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        characterEntity.setMovies(characterDTO.getMovies());
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity characterEntity) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setPicture(characterEntity.getPicture());
        characterDTO.setName(characterEntity.getName());
        characterDTO.setAge(characterEntity.getAge());
        characterDTO.setWeight(characterEntity.getWeight());
        characterDTO.setStory(characterEntity.getStory());
        characterDTO.setMovies(characterEntity.getMovies());
        return characterDTO;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities) {
        List<CharacterDTO> dtoList = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtoList.add(characterEntity2DTO(entity));
        }
        return dtoList;
    }

}
