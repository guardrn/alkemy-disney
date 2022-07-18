package alkemy.disney.service.impl;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.mapper.CharacterMapper;
import alkemy.disney.repository.CharacterRepository;
import alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> characters = characterMapper.characterEntityList2DTOList(entities);
        return characters;
    }

    @Override
    public CharacterDTO getDetailsById(Long id) {
        CharacterEntity characterEntity = characterRepository.findById(id).orElse(null);
        CharacterDTO characterFound = characterMapper.characterEntity2DTO(characterEntity);
        return characterFound;
    }

    @Override
    public CharacterDTO saveCharacter(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity characterEntitySaved = characterRepository.save(characterEntity);
        CharacterDTO savedCharacter = characterMapper.characterEntity2DTO(characterEntitySaved);
        return savedCharacter;
    }

    @Override
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterRepository.getReferenceById(id);
        characterEntity.setPicture(characterDTO.getPicture());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        characterEntity.setMovies(characterDTO.getMovies());
        characterRepository.save(characterEntity);
        CharacterDTO updatedCharacter = characterMapper.characterEntity2DTO(characterEntity);
        return updatedCharacter;
    }

    @Override
    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO getDetailsByName(String name) {
        CharacterEntity characterEntity = characterRepository.getDetailsByName(name);
        CharacterDTO characterFound = characterMapper.characterEntity2DTO(characterEntity);
        return characterFound;
    }

    @Override
    public CharacterDTO getDetailsByMovie(Long idMovie) {
        CharacterEntity characterEntity = characterRepository.getDetailsByMovie(idMovie);
        CharacterDTO characterFound = characterMapper.characterEntity2DTO(characterEntity);
        return characterFound;
    }


}
