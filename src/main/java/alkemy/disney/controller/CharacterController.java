package alkemy.disney.controller;

import alkemy.disney.dto.CharacterDTO;
import alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{idCharacter}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable("idCharacter") Long idCharacter) {
        CharacterDTO character = characterService.getDetailsById(idCharacter);
        return ResponseEntity.ok(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> movies
    ) {
        List<CharacterDTO> characters = characterService.getDetailsByFilter(name, age, movies);
        return ResponseEntity.ok(characters);
    }

    @PostMapping("/save")
    public ResponseEntity<CharacterDTO> saveCharacter(@RequestBody CharacterDTO character) {
        CharacterDTO savedCharacter = characterService.saveCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("update/{idCharacter}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable("idCharacter") Long idCharacter,
                                                        CharacterDTO character) {
        CharacterDTO updatedCharacter = characterService.updateCharacter(idCharacter, character);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCharacter);
    }

    @DeleteMapping("delete/{idCharacter}")
    public ResponseEntity<CharacterDTO> deleteCharacter(@PathVariable("idCharacter") Long idCharacter) {
        characterService.deleteCharacter(idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
