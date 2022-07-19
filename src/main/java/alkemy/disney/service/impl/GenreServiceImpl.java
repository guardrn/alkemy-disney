package alkemy.disney.service.impl;

import alkemy.disney.dto.GenreDTO;
import alkemy.disney.entity.GenreEntity;
import alkemy.disney.mapper.GenreMapper;
import alkemy.disney.repository.GenreRepository;
import alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GenreDTO getDetailsById(Long genreId) {
        GenreEntity genreEntity = genreRepository.findById(genreId).orElse(null);
        return genreMapper.genreEntity2DTO(genreEntity);
    }

    @Override
    public GenreDTO saveGenre(GenreDTO genreDto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDto);
        GenreEntity genreEntitySaved = genreRepository.save(genreEntity);
        return genreMapper.genreEntity2DTO(genreEntitySaved);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDto) {
        GenreEntity genreEntity = genreRepository.getReferenceById(genreId);
        genreMapper.update(genreEntity, genreDto);
        genreRepository.save(genreEntity);
        return genreMapper.genreEntity2DTO(genreEntity);
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

}
