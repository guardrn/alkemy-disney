package alkemy.disney.service;

import alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO getDetailsById(Long id);

    GenreDTO saveGenre(GenreDTO genreDto);

    GenreDTO updateGenre(Long id, GenreDTO genreDto);

    void deleteGenre(Long id);

}
