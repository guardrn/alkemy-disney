package alkemy.disney.service;

import alkemy.disney.dto.CharacterBasicDTO;
import alkemy.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterBasicDTO> getDetailsByFilter(String name, Integer age, List<Long> movies);

    CharacterDTO getDetailsById(Long characterId);

    CharacterDTO saveCharacter(CharacterDTO character);

    CharacterDTO updateCharacter(Long characterId, CharacterDTO character);

    void deleteCharacter(Long characterId);

}
