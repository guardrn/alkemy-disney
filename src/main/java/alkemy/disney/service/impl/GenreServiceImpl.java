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

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> genres = genreMapper.genreEntityList2DTOList(entities);
        return genres;
    }

    @Override
    public GenreDTO getDetailsById(Long id) {
        GenreEntity genreEntity = genreRepository.findById(id).orElse(null);
        GenreDTO genreFound = genreMapper.genreEntity2DTO(genreEntity);
        return genreFound;
    }

    @Override
    public GenreDTO saveGenre(GenreDTO genreDto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDto);
        GenreEntity genreEntitySaved = genreRepository.save(genreEntity);
        GenreDTO savedGenre = genreMapper.genreEntity2DTO(genreEntitySaved);
        return savedGenre;
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO genreDto) {
        GenreEntity genreEntity = genreRepository.getReferenceById(id);
        genreEntity.setName(genreDto.getName());
        genreEntity.setPicture(genreDto.getPicture());
        genreRepository.save(genreEntity);
        GenreDTO updatedGenre = genreMapper.genreEntity2DTO(genreEntity);
        return updatedGenre;
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

}
