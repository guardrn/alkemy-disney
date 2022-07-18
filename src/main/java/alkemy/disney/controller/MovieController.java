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

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable("id") Long id) {
        MovieDTO movie = movieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/save")
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movie) {
        MovieDTO savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("id") Long id, @RequestBody MovieDTO movie) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMovie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(params = "name")
    public ResponseEntity<MovieDTO> getDetailsByName(@RequestParam("name") String name) {
        MovieDTO movie = movieService.getDetailsByName(name);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(params = "genre")
    public ResponseEntity<MovieDTO> getDetailsByGenre(@RequestParam("genre") Long genre) {
        MovieDTO movie = movieService.getDetailsByGenre(genre);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(params = "order")
    public ResponseEntity<List<MovieDTO>> getDetailsByOrder(@RequestParam("order") String order) {
        List<MovieDTO> movies = movieService.getAllMoviesByOrder(order);
        return ResponseEntity.ok().body(movies);
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
