package api.library.study_library.model.service;

import api.library.study_library.model.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author salvarAuthor(Author author);
    Author atualizarAuthor(Author author);
    void deletarAuthor(Author author);
    Optional<Author> buscarAuthor(Long id);
    List<Author> listarAutores();
}
