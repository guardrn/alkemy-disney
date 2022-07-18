package alkemy.disney.controller;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getDetailsById(@PathVariable("id") Long id) {
        GenreDTO genre = genreService.getDetailsById(id);
        return ResponseEntity.ok(genre);
    }

    @PostMapping("/save")
    public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genre) {
        GenreDTO savedGenre = genreService.saveGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") Long id, @RequestBody GenreDTO genre) {
        GenreDTO updatedGenre = genreService.updateGenre(id, genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedGenre);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
