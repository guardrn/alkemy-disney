package alkemy.disney.controller;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/genres")
@Validated
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getDetailsById(@PathVariable("genreId") Long genreId) {
        return ResponseEntity.ok(genreService.getDetailsById(genreId));
    }

    @PostMapping()
    public ResponseEntity<GenreDTO> saveGenre(@Valid @RequestBody GenreDTO genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.saveGenre(genre));
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("genreId") Long genreId,
                                                @Valid @RequestBody GenreDTO genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.updateGenre(genreId, genre));
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
