package alkemy.disney.controller;

import alkemy.disney.dto.MovieBasicDTO;
import alkemy.disney.dto.MovieDTO;
import alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
@Validated
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) String order
    ) {
        return ResponseEntity.ok(movieService.getDetailsByFilter(title, genre, order));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(movieService.getDetailsById(movieId));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movie));
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("movieId") Long movieId,
                                                @Valid @RequestBody MovieDTO movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.updateMovie(movieId, movie));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> saveCharacterInMovie(@PathVariable("movieId") Long movieId,
                                                         @PathVariable("characterId") Long characterId) {
        movieService.saveCharacterInMovie(movieId, characterId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> deleteCharacterInMovie(@PathVariable("movieId") Long movieId,
                                                           @PathVariable("characterId") Long characterId) {
        movieService.deleteCharacterInMovie(movieId, characterId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
