package alkemy.disney.service;

import alkemy.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterDTO> getAllCharacters();

    CharacterDTO getDetailsById(Long id);

    CharacterDTO saveCharacter(CharacterDTO character);

    CharacterDTO updateCharacter(Long id, CharacterDTO character);

    void deleteCharacter(Long id);

    CharacterDTO getDetailsByName(String name);

    CharacterDTO getDetailsByMovie(Long idMovie);

}
