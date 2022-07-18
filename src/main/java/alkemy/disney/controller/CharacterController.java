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
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable("id") Long id) {
        CharacterDTO character = characterService.getDetailsById(id);
        return ResponseEntity.ok().body(character);
    }

    @PostMapping("/save")
    public ResponseEntity<CharacterDTO> saveCharacter(@RequestBody CharacterDTO character) {
        CharacterDTO savedCharacter = characterService.saveCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable("id") Long id, CharacterDTO character) {
        CharacterDTO updatedCharacter = characterService.updateCharacter(id, character);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCharacter);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<CharacterDTO> deleteCharacter(@PathVariable("id") Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params = "name")
    public ResponseEntity<CharacterDTO> getDetailsByName(@RequestParam("name") String name) {
        CharacterDTO character = characterService.getDetailsByName(name);
        return ResponseEntity.ok().body(character);
    }

    @GetMapping(params = "idMovie")
    public ResponseEntity<CharacterDTO> getDetailsByMovie(@RequestParam("idMovie") Long idMovie) {
        CharacterDTO character = characterService.getDetailsByMovie(idMovie);
        return ResponseEntity.ok().body(character);
    }

}
