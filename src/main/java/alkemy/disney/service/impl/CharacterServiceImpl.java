package alkemy.disney.service.impl;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.CharacterFilterDTO;
import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.mapper.CharacterMapper;
import alkemy.disney.repository.CharacterRepository;
import alkemy.disney.repository.specification.CharacterSpecification;
import alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;

    private CharacterSpecification characterSpecification;

    private CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterSpecification characterSpecification,
                                CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        return characterMapper.characterEntityList2DTOList(entities, false);
    }

    @Override
    public CharacterDTO getDetailsById(Long characterId) {
        CharacterEntity characterEntity = characterRepository.findById(characterId).orElse(null);
        return characterMapper.characterEntity2DTO(characterEntity, true);
    }

    @Override
    public List<CharacterDTO> getDetailsByFilter(String name, Integer age, List<Long> movies) {
        CharacterFilterDTO filterDTO = new CharacterFilterDTO(name, age, movies);
        List<CharacterEntity> entities = characterRepository.findAll(
                characterSpecification.getByFilters(filterDTO));
        return characterMapper.characterEntityList2DTOList(entities, true);
    }

    @Override
    public CharacterDTO saveCharacter(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity characterEntitySaved = characterRepository.save(characterEntity);
        return characterMapper.characterEntity2DTO(characterEntitySaved, false);
    }

    @Override
    public CharacterDTO updateCharacter(Long characterId, CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterRepository.getReferenceById(characterId);
        characterMapper.update(characterEntity, characterDTO);
        characterRepository.save(characterEntity);
        return characterMapper.characterEntity2DTO(characterEntity, false);
    }

    @Override
    public void deleteCharacter(Long characterId) {
        characterRepository.deleteById(characterId);
    }

}
