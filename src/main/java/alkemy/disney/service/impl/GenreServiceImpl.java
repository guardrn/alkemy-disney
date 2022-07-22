package alkemy.disney.service.impl;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.entity.GenreEntity;
import alkemy.disney.mapper.GenreMapper;
import alkemy.disney.repository.GenreRepository;
import alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        return genreMapper.genreEntityList2DTOList(entities);
    }

    @Override
    public GenreDTO getDetailsById(Long genreId) throws EntityNotFoundException {
        GenreEntity genreFound = genreRepository.findById(genreId).orElseThrow(()
                -> new EntityNotFoundException("Find by ID: Genre not found"));
        return genreMapper.genreEntity2DTO(genreFound);
    }

    @Override
    public GenreDTO saveGenre(GenreDTO genreDto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDto);
        GenreEntity genreEntitySaved = genreRepository.save(genreEntity);
        return genreMapper.genreEntity2DTO(genreEntitySaved);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDto) throws EntityNotFoundException {
        GenreEntity genreUpdated = genreRepository.findById(genreId).orElseThrow(()
                -> new EntityNotFoundException("Update: Genre not found"));
        genreMapper.update(genreUpdated, genreDto);
        genreRepository.save(genreUpdated);
        return genreMapper.genreEntity2DTO(genreUpdated);
    }

    @Override
    public void deleteGenre(Long genreId) throws EntityNotFoundException {
        genreRepository.findById(genreId).orElseThrow(()
                -> new EntityNotFoundException("Delete: Genre not found"));
        genreRepository.deleteById(genreId);
    }

}
