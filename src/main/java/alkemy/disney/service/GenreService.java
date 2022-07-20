package alkemy.disney.service;

import alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO getDetailsById(Long idGenre);

    GenreDTO saveGenre(GenreDTO genreDto);

    GenreDTO updateGenre(Long idGenre, GenreDTO genreDto);

    void deleteGenre(Long idGenre);

}
