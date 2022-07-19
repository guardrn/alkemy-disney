package alkemy.disney.controller;

import alkemy.disney.dto.MovieDTO;
import alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/allMovies")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable("movieId") Long movieId) {
        MovieDTO movie = movieService.getDetailsById(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) String order
    ) {
        List<MovieDTO> movies = movieService.getDetailsByFilter(title, genre, order);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/save")
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movie) {
        MovieDTO savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PutMapping("/update/{idMovie}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("idMovie") Long idMovie,
                                                @RequestBody MovieDTO movie) {
        MovieDTO updatedMovie = movieService.updateMovie(idMovie, movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMovie);
    }

    @DeleteMapping("/delete/{idMovie}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("idMovie") Long idMovie) {
        movieService.deleteMovie(idMovie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> saveCharacterInMovie(@PathVariable("movieId") Long idMovie,
                                                         @PathVariable("characterId") Long characterId) {
        movieService.saveCharacterInMovie(idMovie, characterId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> deleteCharacterInMovie(@PathVariable("movieId") Long idMovie,
                                                           @PathVariable("characterId") Long characterId) {
        movieService.deleteCharacterInMovie(idMovie, characterId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
