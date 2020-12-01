package api.library.study_library.service;

import api.library.study_library.model.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre salvarGenero(Genre genre);
    Genre atualizarGenero(Genre genre);
    void deletarGenero(Genre genre);
    Optional<Genre> buscarGenero(Integer id);
    Page<Genre> listarGeneros(Pageable page);
}
