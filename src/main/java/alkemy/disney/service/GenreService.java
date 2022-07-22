package alkemy.disney.service;

import alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO getDetailsById(Long genreId);

    GenreDTO saveGenre(GenreDTO genreDto);

    GenreDTO updateGenre(Long genreId, GenreDTO genreDto);

    void deleteGenre(Long genreId);

}
