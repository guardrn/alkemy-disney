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
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getDetailsById(@PathVariable("genreId") Long genreId) {
        GenreDTO genre = genreService.getDetailsById(genreId);
        return ResponseEntity.ok(genre);
    }

    @PostMapping("/save")
    public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genre) {
        GenreDTO savedGenre = genreService.saveGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("update/{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("genreId") Long genreId,
                                                @RequestBody GenreDTO genre) {
        GenreDTO updatedGenre = genreService.updateGenre(genreId, genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedGenre);
    }

    @DeleteMapping("delete/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
