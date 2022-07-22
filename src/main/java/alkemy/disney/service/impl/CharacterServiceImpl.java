package alkemy.disney.service.impl;

import alkemy.disney.dto.CharacterBasicDTO;
import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.dto.CharacterFilterDTO;
import alkemy.disney.entity.CharacterEntity;
import alkemy.disney.mapper.CharacterMapper;
import alkemy.disney.repository.CharacterRepository;
import alkemy.disney.repository.specification.CharacterSpecification;
import alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    private final CharacterSpecification characterSpecification;

    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterSpecification characterSpecification,
                                CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharacterBasicDTO> getDetailsByFilter(String name, Integer age, List<Long> movies) {
        CharacterFilterDTO filtersDTO = new CharacterFilterDTO(name, age, movies);
        List<CharacterEntity> characters = characterRepository.findAll(
                characterSpecification.getByFilters(filtersDTO));
        return characterMapper.characterEntityList2BasicDTOList(characters);
    }

    @Override
    public CharacterDTO getDetailsById(Long characterId) throws EntityNotFoundException {
        CharacterEntity characterFound = characterRepository.findById(characterId).orElseThrow(()
                -> new EntityNotFoundException("Find by ID: Character not found"));
        return characterMapper.characterEntity2DTO(characterFound, true);
    }

    @Override
    public CharacterDTO saveCharacter(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity characterSaved = characterRepository.save(characterEntity);
        return characterMapper.characterEntity2DTO(characterSaved, true);
    }

    @Override
    public CharacterDTO updateCharacter(Long characterId, CharacterDTO characterDTO)
            throws EntityNotFoundException {
        CharacterEntity characterUpdated = characterRepository.findById(characterId).orElseThrow(()
                -> new EntityNotFoundException("Update: Character not found"));
        characterMapper.update(characterUpdated, characterDTO);
        characterRepository.save(characterUpdated);
        return characterMapper.characterEntity2DTO(characterUpdated, true);
    }

    @Override
    public void deleteCharacter(Long characterId) throws EntityNotFoundException {
        characterRepository.findById(characterId).orElseThrow(()
                -> new EntityNotFoundException("Delete: Character not found"));
        characterRepository.deleteById(characterId);
    }

}
