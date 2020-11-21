package api.library.study_library.service;

import api.library.study_library.model.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre salvarGenero(Genre genre);
    Genre atualizarGenero(Genre genre);
    void deletarGenero(Genre genre);
    Optional<Genre> buscarGenero(Integer id);
    List<Genre> listarGeneros();
}
