package alkemy.disney.controller;

import alkemy.disney.dto.CharacterBasicDTO;
import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/characters")
@Validated
public class CharacterController {

    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> movies
    ) {
        return ResponseEntity.ok(characterService.getDetailsByFilter(name, age, movies));
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable("characterId") Long characterId) {
        return ResponseEntity.ok(characterService.getDetailsById(characterId));
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> saveCharacter(@Valid @RequestBody CharacterDTO character) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.saveCharacter(character));
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable("characterId") Long characterId,
                                                        @Valid @RequestBody CharacterDTO character) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.updateCharacter(
                characterId, character));
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<CharacterDTO> deleteCharacter(@PathVariable("characterId") Long characterId) {
        characterService.deleteCharacter(characterId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
