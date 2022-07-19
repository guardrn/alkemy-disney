package alkemy.disney.service;

import alkemy.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterDTO> getAllCharacters();

    CharacterDTO getDetailsById(Long characterId);

    List<CharacterDTO> getDetailsByFilter(String name, Integer age, List<Long> movies);

    CharacterDTO saveCharacter(CharacterDTO character);

    CharacterDTO updateCharacter(Long characterId, CharacterDTO character);

    void deleteCharacter(Long characterId);

}
