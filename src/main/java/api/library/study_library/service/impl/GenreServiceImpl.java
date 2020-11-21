package api.library.study_library.service.impl;

import api.library.study_library.model.entity.Genre;
import api.library.study_library.repository.GenreRepository;
import api.library.study_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository repository;


    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Genre salvarGenero(Genre genre) {
        return repository.save(genre);
    }

    @Override
    @Transactional
    public Genre atualizarGenero(Genre genre) {
        return repository.save(genre);
    }

    @Override
    @Transactional
    public void deletarGenero(Genre genre) {
        repository.delete(genre);
    }

    @Override
    public Optional<Genre> buscarGenero(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Genre> listarGeneros() {
        return repository.findAll();
    }


}
